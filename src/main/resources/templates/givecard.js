
//工具方法:判空
function isnull(str){if(str==null||str==""||str=="undefine")return true;
    return false;
}

//工具方法:关闭控件
function yktEnd(){
    ret = yktReader.yktend();
    if (ret != 0 ){
        alert("控件关闭失败" + ret);
        alert("请关闭浏览器重新操作");
    }
}
//地址栏取值
function getParamFromUrl( name, url ) {
    if (!url) {
        url = location.href;
    }
    name = name.replace(/[\[]/,'\\\[').replace(/[\]]/,'\\\]');
    var regexS = '[\\?&]'+name+'=([^&#]*)';
    var regex = new RegExp( regexS );
    var results = regex.exec( url );
    return results === null ? null : results[1];
}

// 【签到】
function bkcheckin() {
    var ret = 0
    var unitid = '75113001' // 单位号
    var netid = '003080000001' // 商户号

    var ip = '172.30.0.61' // IP地址
    var port = '5001' // 通讯端口

    var yktReader = document.getElementById('yktReader')
    ret = yktReader.yktinit(ip, port)

    if(ret != 0){
        alert("初始化控件失败。" + ret);
        yktEnd();
        return;
    }

    // 读sam卡号
    posid1 = yktReader.YKTReadSAMNO()

    if (posid1 === null || posid1 === '') {
        alert("读sam卡失败!");
        yktEnd();
        return;
    }

    ret = yktReader.SetUnit(unitid)
    if (ret !== 0) {
        alert('设置参数失败!' + ret)
        return
    }

    ret = yktReader.SetMchntid(netid)
    if (ret !== 0) {
        alert('设置参数失败!' + ret)
        return
    }
    ret = yktReader.SetPosId(posid1)
    if (ret !== 0) {
        alert('设置参数失败!' + ret)
        return
    }

    ret = yktReader.YktposCheckin()
    if (ret !== 0) {
        alert('签到失败,请重新签到!' + ret)
        yktEnd()
        return
    } else {
        alert('签到成功!' + ret)
        yktEnd()
        return
    }
}

// 获取sNewCardInfo
function sNewCardInfo(citizenId) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sNewCardInfo/" + citizenId,
        // url: "/cardcheck/sNewCardInfo/" + citizenId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                $("#sNewCardInfo").val(result.data);
            } else {
            }
        }
    });
}
// 获取sInfoData
function sInfoData(citizenId) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sInfoData/" + citizenId,
        // url: "/cardcheck/sInfoData/" + citizenId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                $("#sInfoData").val(result.data);
            } else {
            }
        }
    });
}

// 获取sYearInfo
function sYearInfo(requestIdCard) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sYearInfo/" + requestIdCard,
        // url: "/cardcheck/sYearInfo/" + requestIdCard,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                $("#sYearInfo").val(result.data);
            } else {
            }
        }
    });
}

// 通过requestId从cdpf_card查询单条数据
function getCdpfCard(requestId) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cards/" + requestId,
        // url: "/cards/" + requestId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                $("#cardnoCheck").val(result.data.cardNo);
                $("#cardBusiness").val(result.data.cardBusiness);
                $("#cardMakeState").val(result.data.cardMakeState);
                $("#oldCardNo").val(result.data.oldCardNo);
            } else {
            }
        }
    });
}


var posid1 = ''
// 确认发卡
// modified at 2016.04.12 处理卡延期260的问题
function save_givedCard(check260) {
    // 正常发卡
    if (check260 === null || check260 === '') {
        if (!confirm('您确认已经发卡吗？')) return
    }
    // 卡标记位非空确认，卡标记为在最开始已经校验
    var cardNoFlagTemp = document.getElementById('cardNoFlagTemp').value // 卡标记位
    document.getElementById('cardNoFlagT').value = cardNoFlagTemp
    var cardBusiness = document.getElementById('cardBusiness').value // 卡流程值
    var cardmakestate = document.getElementById('cardmakestate').value // 制卡状态
    // console.log('test卡标记位：' + cardNoFlagTemp);
    // console.log('test卡流程值：' + cardBusiness);
    // console.log('test制卡状态：' + cardmakestate);
    // add at 2016.03.04 by liqi
    // 发卡时验证一卡通号一致
    var cardnoCheck = document.getElementById('cardnoCheck').value
    // console.log('test-cardnoCheck：' + cardnoCheck);
    var newCardNo = ''

    if (cardBusiness === '110' || cardBusiness === '111' || cardmakestate === '4') {
        /*
         *北控接口---开始
         */
        var ret = 0
        var unitid = document.getElementById('unit').value // 单位号
        var netid = document.getElementById('Mchnt').value // 商户号
        var ip = document.getElementById('ip_addr').value // IP地址
        var port = document.getElementById('serverport').value // 通讯端口

        var yktReader = document.getElementById('yktReader')
        ret = yktReader.yktinit(ip, port)
        if(ret != 0){
            alert("初始化控件失败。" + ret);
            yktEnd();
            return;
        }
        posid1 = yktReader.YKTReadSAMNO()
        if (posid1 === null || posid1 === '') {
            alert('读sam卡失败!')
            return
        }

        var posid = posid1 // 设备编号(Pos机号)
        var oprid = document.getElementById('opr').value // 操作员代码
        var info = document.getElementById('sInfoData').value
        try {
            ret = yktReader.SetUnit(unitid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            ret = yktReader.SetMchntid(netid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            ret = yktReader.SetPosId(posid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            ret = yktReader.SetOprId(oprid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            // modified at 2015.05.11 by liqi
            var info1 = yktReader.YKTReadCard() // 卡号,有效期
            var cardNo = ''
            //正常发卡
            if(isnull(check260)){
                // 正常发卡
                if(info1 == "") {
                    info1 = yktReader.YKTReadCard1();    //卡号,有效期
                    cardNo = info1.split(",")[0]; //获取卡号

                    var sInfoData = cardNo + "," + info + "," + cardNoFlagTemp;

                    //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                    //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                    ret = yktReader.YKTenableCard(sInfoData);
                    if(ret != 0){
                        yktEnd();
                        if(ret == 6013 || ret == 156){
                            alert("卡片发行失败,请重新签到：" + ret);
                        }else if (ret == 326){
                            alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                        }else if (ret >= 5012 && ret <=5015){
                            alert("当前网络连接失败,请重试:" + ret);
                        }else{
                            if(!isnull(check260) && check260 == 6006){
                                alert("卡片发行失败:" + ret +",请进行卡残损换新");
                            }else{
                                alert("卡片发行失败:" + ret);
                            }
                        }
                        return;
                    }
                }else{
                    //info1非空
                    cardNo = info1.split(",")[0]; //获取卡号

                    var sInfoData = cardNo + "," + info + "," + cardNoFlagTemp;
                    //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                    //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                    ret = yktReader.YKTenableCard(sInfoData);
                    if(ret != 0){
                        yktEnd();
                        if(ret == 6013 || ret == 156){
                            alert("卡片发行失败,请重新签到：" + ret);
                        }else if (ret == 326){
                            alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                        }else if (ret >= 5012 && ret <=5015){
                            alert("当前网络连接失败,请重试:" + ret);
                        }else{
                            if(!isnull(check260) && check260 == 6006){
                                alert("卡片发行失败:" + ret +",请进行卡残损换新");
                            }else{
                                alert("卡片发行失败:" + ret);
                            }
                        }
                        return;
                    }

                }
            }else{
                //卡延期失败发卡
                if(isnull(info1)){
                    info1 = yktReader.YKTReadCard1();    //卡号,有效期
                }

                if(isnull(info1)){
                    yktEnd();
                    alert('卡片信息为空，请联系证卡系统维护人员');
                    return;
                }

                cardNo = info1.split(",")[0]; //获取卡号

                var sInfoData = cardNo + "," + info + "," + cardNoFlagTemp;

                //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                ret = yktReader.YKTenableCard(sInfoData);
                if(ret != 0){
                    yktEnd();
                    if(ret == 6013 || ret == 156){
                        alert("卡片发行失败,请重新签到：" + ret);
                    }else if (ret == 326){
                        alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                    }else if (ret >= 5012 && ret <=5015){
                        alert("当前网络连接失败,请重试:" + ret);
                    }else{
                        if(!isnull(check260) && check260 == 6006){
                            alert("卡片发行失败:" + ret +",请进行卡残损换新");
                        }else{
                            alert("卡片发行失败:" + ret);
                        }
                    }
                    return;
                }
            }

            // yktEnd()
            ret = yktReader.yktend() // 关闭控件
            if (ret !== 0) {
                alert('控件关闭失败' + ret)
                alert('请关闭浏览器重新操作')
            }
        } catch (e) {
            alert(e.message)
        }

        /*
         * 北控接口---结束
         */
        document.getElementById('newCardNo').value = cardNo // 第一次发卡，新卡号等于从延期机器读出的卡号
    } else if (cardBusiness == '120'|| cardBusiness == '1209') {
        /*
         *北控接口---开始
         */
        var ret = 0

        var unitid = document.getElementById('unit').value // 单位号
        var netid = document.getElementById('Mchnt').value // 商户号

        var ip = document.getElementById('ip_addr').value // IP地址
        var port = document.getElementById('serverport').value // 通讯端口
        var yktReader = document.getElementById('yktReader')
        ret = yktReader.YKTInit(ip,port);
        if(ret != 0){
            alert("初始化控件失败。" + ret);
            yktEnd();
            return;
        }

        posid1 = yktReader.YKTReadSAMNO()
        if (posid1 === null || posid1 === '') {
            alert('读sam卡失败!')
            return
        }

        var posid = posid1 // 设备编号(Pos机号)
        var oprid = document.getElementById('opr').value // 操作员代码
        var info = document.getElementById('sNewCardInfo').value
        // var sNewInfo = document.getElementById('sNewInfo').value // 残疾人卡信息更改
        try {
            ret = yktReader.SetUnit(unitid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }
            ret = yktReader.SetMchntid(netid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }
            ret = yktReader.SetPosId(posid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }
            ret = yktReader.SetOprId(oprid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            // modified at 2015.05.11 by liqi
            var info1 = yktReader.YKTReadCard() // 卡号,有效期
            var newCardNo = ''
            if(isnull(check260)){
                if (info1 === '') {
                    info1 = yktReader.YKTReadCard1() // 卡号,有效期
                    var oldCardNo = document.getElementById('oldCardNo').value // 获取原卡号
                    newCardNo = info1.split(',')[0] // 获取新卡号

                    // 比较一卡通号
                    // 不一致不允许发卡
                    if (cardnoCheck !== null && cardnoCheck !== '') {
                        if (newCardNo !== cardnoCheck) {
                            // yktEnd()
                            ret = yktReader.yktend() // 关闭控件
                            if (ret !== 0) {
                                alert('控件关闭失败' + ret)
                                alert('请关闭浏览器重新操作')
                            }
                            alert('请确认此残疾人卡一卡通号与证卡系统内残疾人一卡通号一致')
                            return
                        }
                    }

                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;
                    //原卡号,新卡号,姓名,移动电话,账户密码,身份证,卡校验位
                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28 || ret == 39){
                            //modified at 2015.12.24 by liqi
                            var infoNew = $('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //卡号,有效期

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //卡号,有效期
                                newCardNo = info1.split(",")[0]; //获取卡号
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                                //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("卡片发行失败,请重新签到:" + ret );
                                    }else if (ret == 326){
                                        alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("当前网络连接失败,请重试:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("卡片发行失败:" + ret +",请进行卡残损换新");
                                        }else{
                                            alert("卡片发行失败:" + ret);
                                        }
                                    }
                                    yktEnd();
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //获取卡号
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("卡片丢失补卡失败:" + ret);
                            return;
                        }
                    }
                } else {
                    newCardNo = info1.split(',')[0] // 获取卡号
                    var oldCardNo = document.getElementById('oldCardNo').value // 获取原卡号
                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;

                    //原卡号,新卡号,姓名,移动电话,账户密码,身份证,卡校验位
                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28){
                            //modified at 2015.12.24 by liqi
                            var infoNew = document.getElementById('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //卡号,有效期

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //卡号,有效期
                                newCardNo = info1.split(",")[0]; //获取卡号
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                                //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("卡片发行失败,请重新签到:" + ret );
                                    }else if (ret == 326){
                                        alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("当前网络连接失败,请重试:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("卡片发行失败:" + ret +",请进行卡残损换新");
                                        }else{
                                            alert("卡片发行失败:" + ret);
                                        }
                                    }
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //获取卡号
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("卡片丢失补卡失败:" + ret);
                            return;
                        }
                    }
                }
            }else {
                //卡延期失败，重新发卡
                if(isnull(info1)){
                    info1 = yktReader.YKTReadCard1();    //卡号,有效期
                }
                if(isnull(info1)){
                    yktEnd();
                    alert('卡内信息为空');
                    return;
                }


                var oldCardNo = $('oldCardNo').value; //获取原卡号
                newCardNo = info1.split(",")[0];  //获取新卡号

                var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;

                //原卡号,新卡号,姓名,移动电话,账户密码,身份证,卡校验位
                ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                if(ret != 0){
                    if(ret == 28){
                        //modified at 2015.12.24 by liqi
                        var infoNew = $('sInfoData').value;
                        var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                        //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                        ret = yktReader.YKTenableCard(sInfoData);
                        if(ret != 0){
                            yktEnd();
                            if(ret == 6013 || ret == 156){
                                alert("卡片发行失败,请重新签到:" + ret );
                            }else if (ret == 326){
                                alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                            }else if (ret >= 5012 && ret <=5015){
                                alert("当前网络连接失败,请重试:" + ret);
                            }else{
                                if(!isnull(check260) && check260 == 6006){
                                    alert("卡片发行失败:" + ret +",请进行卡残损换新");
                                }else{
                                    alert("卡片发行失败:" + ret);
                                }
                            }
                            return;
                        }

                    }else if (ret == 326){
                        yktEnd();
                        alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                        return;
                    }else{
                        yktEnd();
                        alert("卡片丢失补卡失败:" + ret);
                        return;
                    }
                }

            }
            yktEnd()
        } catch (e) { alert(e.message) }

        /*
             * 北控接口---结束
         */

        document.getElementById('newCardNo').value = newCardNo // 补办发卡新卡号从延期机器读取
    } else if (cardBusiness == '140'|| cardBusiness == '1409') {
        /*
         *北控接口---开始
         */
        var ret = 0

        var unitid = document.getElementById('unit').value // 单位号
        var netid = document.getElementById('Mchnt').value // 商户号

        var ip = document.getElementById('ip_addr').value // IP地址
        var port = document.getElementById('serverport').value // 通讯端口
        var yktReader = document.getElementById('yktReader')
        ret = yktReader.YKTInit(ip, port)
        if(ret != 0){
            alert("初始化控件失败。" + ret);
            yktEnd();
            return;
        }

        posid1 = yktReader.YKTReadSAMNO()
        if (posid1 === null || posid1 === '') {
            alert('读sam卡失败!')
            return
        }

        var posid = posid1 // 设备编号(Pos机号)
        var oprid = document.getElementById('opr').value // 操作员代码
        var info = document.getElementById('sNewCardInfo').value
        // var sNewInfo = document.getElementById('sNewInfo').value // 残疾人卡信息更改
        try {
            ret = yktReader.SetUnit(unitid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            ret = yktReader.SetMchntid(netid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            ret = yktReader.SetPosId(posid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            ret = yktReader.SetOprId(oprid)
            if (ret !== 0) {
                alert('设置参数失败!' + ret)
                return
            }

            // modified at 2015.05.11 by liqi
            var info1 = yktReader.YKTReadCard();    //卡号,有效期
            var newCardNo = "";

            // 正常发卡
            if (check260 === null || check260 === '') {
                if (info1 === '') {
                    info1 = yktReader.YKTReadCard1() // 卡号,有效期
                    var oldCardNo = document.getElementById('oldCardNo').value // 获取原卡号
                    newCardNo = info1.split(',')[0] // 获取新卡号

                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;

                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28){
                            //modified at 2015.12.24 by liqi
                            var infoNew = $('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //卡号,有效期

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //卡号,有效期
                                newCardNo = info1.split(",")[0]; //获取卡号
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                                //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("卡片发行失败,请重新签到：" + ret);
                                    }else if (ret == 326){
                                        alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("当前网络连接失败,请重试:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("卡片发行失败:" + ret +",请进行卡残损换新");
                                        }else{
                                            alert("卡片发行失败:" + ret);
                                        }
                                    }
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //获取卡号
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("坏卡补卡失败:" + ret);
                            return;
                        }
                    }
                } else {
                    newCardNo = info1.split(",")[0]; //获取卡号
                    var oldCardNo = document.getElementById('oldCardNo').value // 获取原卡号

                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;
                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28){
                            //modified at 2015.12.24 by liqi
                            var infoNew = $('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //卡号,有效期

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //卡号,有效期
                                newCardNo = info1.split(",")[0]; //获取卡号
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //卡号,有效期,残疾人编码,姓名, 身份证号,移动电话号码,家庭电话,
                                //办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("卡片发行失败,请重新签到：" + ret);
                                    }else if (ret == 326){
                                        alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("当前网络连接失败,请重试:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("卡片发行失败:" + ret +",请进行卡残损换新");
                                        }else{
                                            alert("卡片发行失败:" + ret);
                                        }
                                    }
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //获取卡号
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("卡校验位错误，请输入正确的卡校验位：" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("坏卡补卡失败:" + ret);
                            return;
                        }
                    }
                }
            } else {
                // 卡延期失败，重新发卡
                if (info1 === null || info1 === '') {
                    info1 = yktReader.YKTReadCard1() // 卡号,有效期
                }

                if (info1 === null || info1 === '') {
                    yktEnd()
                    alert('卡内信息为空')
                    return
                }

                var oldCardNo = document.getElementById('oldCardNo').value // 获取原卡号
                newCardNo = info1.split(',')[0] // 获取新卡号


                var sNewCardInfo = oldCardNo + ',' + newCardNo + ',' + info + ',' + cardNoFlagTemp

                ret = yktReader.YKTsuppleBadCard(sNewCardInfo)

                if (ret !== 0) {
                    if (ret === 28 || ret === 39) {
                        var infoNew = document.getElementById('sInfoData').value
                        var sInfoData = newCardNo + ',' + infoNew + ',' + cardNoFlagTemp

                        // 办公电话,email,户籍地址 ,邮编,交易密码,查询密码,卡校验
                        ret = yktReader.YKTenableCard(sInfoData)
                        if (ret !== 0) {
                            yktEnd()
                            if (ret === 6013 || ret === 156) {
                                alert('卡片发行失败,请重新签到：' + ret)
                            } else if (ret === 326) {
                                alert('卡校验位错误，请输入正确的卡校验位：' + ret)
                            } else if (ret >= 5012 && ret <= 5015) {
                                alert('当前网络连接失败,请重试:' + ret)
                            } else {
                                if (check260 !== null && check260 !== '' && check260 === 6006) {
                                    alert('卡片发行失败:' + ret + ',请进行卡残损换新')
                                } else {
                                    alert('卡片发行失败:' + ret)
                                }
                            }
                            return
                        }
                    } else if (ret === 326) {
                        yktEnd()
                        alert('卡校验位错误，请输入正确的卡校验位：' + ret)
                        return
                    } else {
                        yktEnd()
                        alert('坏卡补卡失败:' + ret)
                        return
                    }
                }
            }

            yktEnd()
        } catch (e) { alert(e.message) }

        /*
             * 北控接口---结束
             */

        document.getElementById('newCardNo').value = newCardNo // 补办发卡新卡号从延期机器读取
    }
    // add at 2016.04.12 添加卡延期260错误处理 字段
    if (check260 !== null && check260 !== '' && check260 !== 0) {
        document.getElementById('check260').value = check260
    }
    return true
}
// 新增办卡或因证办卡
function cardBusiness110or111or4(check260,cardnoCheck,cardNoFlagTemp) {
}
// 卡补办确认发卡
function cardBusiness120(check260,cardnoCheck,cardNoFlagTemp){
}
// 卡残损确认发卡
function cardBusiness140(check260,cardnoCheck,cardNoFlagTemp) {
}

// 发卡 提交到数据库
function givedCard() {

    var param = {};
    param.requestIdCard = $("#requestIdCard").val();
    param.check260 = $("#check260").val();
    param.cardNoFlagT = $("#cardNoFlagT").val();
    param.newCardNo = $("#newCardNo").val();
    // console.log(param)
    $.ajax({
        type: "put",
        url: "/bdpfnew/cardGive",
        // url: "/cardGive",
        dateType:"JSON",
        data : JSON.stringify(param),
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                alert(result.content)
            } else {
                alert(result.content)
            }
        }
    });
}