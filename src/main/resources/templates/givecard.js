
//���߷���:�п�
function isnull(str){if(str==null||str==""||str=="undefine")return true;
    return false;
}

//���߷���:�رտؼ�
function yktEnd(){
    ret = yktReader.yktend();
    if (ret != 0 ){
        alert("�ؼ��ر�ʧ��" + ret);
        alert("��ر���������²���");
    }
}
//��ַ��ȡֵ
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

// ��ǩ����
function bkcheckin() {
    var ret = 0
    var unitid = '75113001' // ��λ��
    var netid = '003080000001' // �̻���

    var ip = '172.30.0.61' // IP��ַ
    var port = '5001' // ͨѶ�˿�

    var yktReader = document.getElementById('yktReader')
    ret = yktReader.yktinit(ip, port)

    if(ret != 0){
        alert("��ʼ���ؼ�ʧ�ܡ�" + ret);
        yktEnd();
        return;
    }

    // ��sam����
    posid1 = yktReader.YKTReadSAMNO()

    if (posid1 === null || posid1 === '') {
        alert("��sam��ʧ��!");
        yktEnd();
        return;
    }

    ret = yktReader.SetUnit(unitid)
    if (ret !== 0) {
        alert('���ò���ʧ��!' + ret)
        return
    }

    ret = yktReader.SetMchntid(netid)
    if (ret !== 0) {
        alert('���ò���ʧ��!' + ret)
        return
    }
    ret = yktReader.SetPosId(posid1)
    if (ret !== 0) {
        alert('���ò���ʧ��!' + ret)
        return
    }

    ret = yktReader.YktposCheckin()
    if (ret !== 0) {
        alert('ǩ��ʧ��,������ǩ��!' + ret)
        yktEnd()
        return
    } else {
        alert('ǩ���ɹ�!' + ret)
        yktEnd()
        return
    }
}

// ��ȡsNewCardInfo
function sNewCardInfo(citizenId) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sNewCardInfo/" + citizenId,
        // url: "/cardcheck/sNewCardInfo/" + citizenId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                $("#sNewCardInfo").val(result.data);
            } else {
            }
        }
    });
}
// ��ȡsInfoData
function sInfoData(citizenId) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sInfoData/" + citizenId,
        // url: "/cardcheck/sInfoData/" + citizenId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                $("#sInfoData").val(result.data);
            } else {
            }
        }
    });
}

// ��ȡsYearInfo
function sYearInfo(requestIdCard) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sYearInfo/" + requestIdCard,
        // url: "/cardcheck/sYearInfo/" + requestIdCard,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                $("#sYearInfo").val(result.data);
            } else {
            }
        }
    });
}

// ͨ��requestId��cdpf_card��ѯ��������
function getCdpfCard(requestId) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cards/" + requestId,
        // url: "/cards/" + requestId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
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
// ȷ�Ϸ���
// modified at 2016.04.12 ��������260������
function save_givedCard(check260) {
    // ��������
    if (check260 === null || check260 === '') {
        if (!confirm('��ȷ���Ѿ�������')) return
    }
    // �����λ�ǿ�ȷ�ϣ������Ϊ���ʼ�Ѿ�У��
    var cardNoFlagTemp = document.getElementById('cardNoFlagTemp').value // �����λ
    document.getElementById('cardNoFlagT').value = cardNoFlagTemp
    var cardBusiness = document.getElementById('cardBusiness').value // ������ֵ
    var cardmakestate = document.getElementById('cardmakestate').value // �ƿ�״̬
    // console.log('test�����λ��' + cardNoFlagTemp);
    // console.log('test������ֵ��' + cardBusiness);
    // console.log('test�ƿ�״̬��' + cardmakestate);
    // add at 2016.03.04 by liqi
    // ����ʱ��֤һ��ͨ��һ��
    var cardnoCheck = document.getElementById('cardnoCheck').value
    // console.log('test-cardnoCheck��' + cardnoCheck);
    var newCardNo = ''

    if (cardBusiness === '110' || cardBusiness === '111' || cardmakestate === '4') {
        /*
         *���ؽӿ�---��ʼ
         */
        var ret = 0
        var unitid = document.getElementById('unit').value // ��λ��
        var netid = document.getElementById('Mchnt').value // �̻���
        var ip = document.getElementById('ip_addr').value // IP��ַ
        var port = document.getElementById('serverport').value // ͨѶ�˿�

        var yktReader = document.getElementById('yktReader')
        ret = yktReader.yktinit(ip, port)
        if(ret != 0){
            alert("��ʼ���ؼ�ʧ�ܡ�" + ret);
            yktEnd();
            return;
        }
        posid1 = yktReader.YKTReadSAMNO()
        if (posid1 === null || posid1 === '') {
            alert('��sam��ʧ��!')
            return
        }

        var posid = posid1 // �豸���(Pos����)
        var oprid = document.getElementById('opr').value // ����Ա����
        var info = document.getElementById('sInfoData').value
        try {
            ret = yktReader.SetUnit(unitid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            ret = yktReader.SetMchntid(netid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            ret = yktReader.SetPosId(posid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            ret = yktReader.SetOprId(oprid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            // modified at 2015.05.11 by liqi
            var info1 = yktReader.YKTReadCard() // ����,��Ч��
            var cardNo = ''
            //��������
            if(isnull(check260)){
                // ��������
                if(info1 == "") {
                    info1 = yktReader.YKTReadCard1();    //����,��Ч��
                    cardNo = info1.split(",")[0]; //��ȡ����

                    var sInfoData = cardNo + "," + info + "," + cardNoFlagTemp;

                    //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                    //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                    ret = yktReader.YKTenableCard(sInfoData);
                    if(ret != 0){
                        yktEnd();
                        if(ret == 6013 || ret == 156){
                            alert("��Ƭ����ʧ��,������ǩ����" + ret);
                        }else if (ret == 326){
                            alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                        }else if (ret >= 5012 && ret <=5015){
                            alert("��ǰ��������ʧ��,������:" + ret);
                        }else{
                            if(!isnull(check260) && check260 == 6006){
                                alert("��Ƭ����ʧ��:" + ret +",����п�������");
                            }else{
                                alert("��Ƭ����ʧ��:" + ret);
                            }
                        }
                        return;
                    }
                }else{
                    //info1�ǿ�
                    cardNo = info1.split(",")[0]; //��ȡ����

                    var sInfoData = cardNo + "," + info + "," + cardNoFlagTemp;
                    //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                    //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                    ret = yktReader.YKTenableCard(sInfoData);
                    if(ret != 0){
                        yktEnd();
                        if(ret == 6013 || ret == 156){
                            alert("��Ƭ����ʧ��,������ǩ����" + ret);
                        }else if (ret == 326){
                            alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                        }else if (ret >= 5012 && ret <=5015){
                            alert("��ǰ��������ʧ��,������:" + ret);
                        }else{
                            if(!isnull(check260) && check260 == 6006){
                                alert("��Ƭ����ʧ��:" + ret +",����п�������");
                            }else{
                                alert("��Ƭ����ʧ��:" + ret);
                            }
                        }
                        return;
                    }

                }
            }else{
                //������ʧ�ܷ���
                if(isnull(info1)){
                    info1 = yktReader.YKTReadCard1();    //����,��Ч��
                }

                if(isnull(info1)){
                    yktEnd();
                    alert('��Ƭ��ϢΪ�գ�����ϵ֤��ϵͳά����Ա');
                    return;
                }

                cardNo = info1.split(",")[0]; //��ȡ����

                var sInfoData = cardNo + "," + info + "," + cardNoFlagTemp;

                //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                ret = yktReader.YKTenableCard(sInfoData);
                if(ret != 0){
                    yktEnd();
                    if(ret == 6013 || ret == 156){
                        alert("��Ƭ����ʧ��,������ǩ����" + ret);
                    }else if (ret == 326){
                        alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                    }else if (ret >= 5012 && ret <=5015){
                        alert("��ǰ��������ʧ��,������:" + ret);
                    }else{
                        if(!isnull(check260) && check260 == 6006){
                            alert("��Ƭ����ʧ��:" + ret +",����п�������");
                        }else{
                            alert("��Ƭ����ʧ��:" + ret);
                        }
                    }
                    return;
                }
            }

            // yktEnd()
            ret = yktReader.yktend() // �رտؼ�
            if (ret !== 0) {
                alert('�ؼ��ر�ʧ��' + ret)
                alert('��ر���������²���')
            }
        } catch (e) {
            alert(e.message)
        }

        /*
         * ���ؽӿ�---����
         */
        document.getElementById('newCardNo').value = cardNo // ��һ�η������¿��ŵ��ڴ����ڻ��������Ŀ���
    } else if (cardBusiness == '120'|| cardBusiness == '1209') {
        /*
         *���ؽӿ�---��ʼ
         */
        var ret = 0

        var unitid = document.getElementById('unit').value // ��λ��
        var netid = document.getElementById('Mchnt').value // �̻���

        var ip = document.getElementById('ip_addr').value // IP��ַ
        var port = document.getElementById('serverport').value // ͨѶ�˿�
        var yktReader = document.getElementById('yktReader')
        ret = yktReader.YKTInit(ip,port);
        if(ret != 0){
            alert("��ʼ���ؼ�ʧ�ܡ�" + ret);
            yktEnd();
            return;
        }

        posid1 = yktReader.YKTReadSAMNO()
        if (posid1 === null || posid1 === '') {
            alert('��sam��ʧ��!')
            return
        }

        var posid = posid1 // �豸���(Pos����)
        var oprid = document.getElementById('opr').value // ����Ա����
        var info = document.getElementById('sNewCardInfo').value
        // var sNewInfo = document.getElementById('sNewInfo').value // �м��˿���Ϣ����
        try {
            ret = yktReader.SetUnit(unitid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }
            ret = yktReader.SetMchntid(netid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }
            ret = yktReader.SetPosId(posid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }
            ret = yktReader.SetOprId(oprid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            // modified at 2015.05.11 by liqi
            var info1 = yktReader.YKTReadCard() // ����,��Ч��
            var newCardNo = ''
            if(isnull(check260)){
                if (info1 === '') {
                    info1 = yktReader.YKTReadCard1() // ����,��Ч��
                    var oldCardNo = document.getElementById('oldCardNo').value // ��ȡԭ����
                    newCardNo = info1.split(',')[0] // ��ȡ�¿���

                    // �Ƚ�һ��ͨ��
                    // ��һ�²�������
                    if (cardnoCheck !== null && cardnoCheck !== '') {
                        if (newCardNo !== cardnoCheck) {
                            // yktEnd()
                            ret = yktReader.yktend() // �رտؼ�
                            if (ret !== 0) {
                                alert('�ؼ��ر�ʧ��' + ret)
                                alert('��ر���������²���')
                            }
                            alert('��ȷ�ϴ˲м��˿�һ��ͨ����֤��ϵͳ�ڲм���һ��ͨ��һ��')
                            return
                        }
                    }

                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;
                    //ԭ����,�¿���,����,�ƶ��绰,�˻�����,���֤,��У��λ
                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28 || ret == 39){
                            //modified at 2015.12.24 by liqi
                            var infoNew = $('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //����,��Ч��

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //����,��Ч��
                                newCardNo = info1.split(",")[0]; //��ȡ����
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                                //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("��Ƭ����ʧ��,������ǩ��:" + ret );
                                    }else if (ret == 326){
                                        alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("��ǰ��������ʧ��,������:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("��Ƭ����ʧ��:" + ret +",����п�������");
                                        }else{
                                            alert("��Ƭ����ʧ��:" + ret);
                                        }
                                    }
                                    yktEnd();
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //��ȡ����
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("��Ƭ��ʧ����ʧ��:" + ret);
                            return;
                        }
                    }
                } else {
                    newCardNo = info1.split(',')[0] // ��ȡ����
                    var oldCardNo = document.getElementById('oldCardNo').value // ��ȡԭ����
                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;

                    //ԭ����,�¿���,����,�ƶ��绰,�˻�����,���֤,��У��λ
                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28){
                            //modified at 2015.12.24 by liqi
                            var infoNew = document.getElementById('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //����,��Ч��

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //����,��Ч��
                                newCardNo = info1.split(",")[0]; //��ȡ����
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                                //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("��Ƭ����ʧ��,������ǩ��:" + ret );
                                    }else if (ret == 326){
                                        alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("��ǰ��������ʧ��,������:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("��Ƭ����ʧ��:" + ret +",����п�������");
                                        }else{
                                            alert("��Ƭ����ʧ��:" + ret);
                                        }
                                    }
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //��ȡ����
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("��Ƭ��ʧ����ʧ��:" + ret);
                            return;
                        }
                    }
                }
            }else {
                //������ʧ�ܣ����·���
                if(isnull(info1)){
                    info1 = yktReader.YKTReadCard1();    //����,��Ч��
                }
                if(isnull(info1)){
                    yktEnd();
                    alert('������ϢΪ��');
                    return;
                }


                var oldCardNo = $('oldCardNo').value; //��ȡԭ����
                newCardNo = info1.split(",")[0];  //��ȡ�¿���

                var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;

                //ԭ����,�¿���,����,�ƶ��绰,�˻�����,���֤,��У��λ
                ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                if(ret != 0){
                    if(ret == 28){
                        //modified at 2015.12.24 by liqi
                        var infoNew = $('sInfoData').value;
                        var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                        //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                        ret = yktReader.YKTenableCard(sInfoData);
                        if(ret != 0){
                            yktEnd();
                            if(ret == 6013 || ret == 156){
                                alert("��Ƭ����ʧ��,������ǩ��:" + ret );
                            }else if (ret == 326){
                                alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                            }else if (ret >= 5012 && ret <=5015){
                                alert("��ǰ��������ʧ��,������:" + ret);
                            }else{
                                if(!isnull(check260) && check260 == 6006){
                                    alert("��Ƭ����ʧ��:" + ret +",����п�������");
                                }else{
                                    alert("��Ƭ����ʧ��:" + ret);
                                }
                            }
                            return;
                        }

                    }else if (ret == 326){
                        yktEnd();
                        alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                        return;
                    }else{
                        yktEnd();
                        alert("��Ƭ��ʧ����ʧ��:" + ret);
                        return;
                    }
                }

            }
            yktEnd()
        } catch (e) { alert(e.message) }

        /*
             * ���ؽӿ�---����
         */

        document.getElementById('newCardNo').value = newCardNo // ���췢���¿��Ŵ����ڻ�����ȡ
    } else if (cardBusiness == '140'|| cardBusiness == '1409') {
        /*
         *���ؽӿ�---��ʼ
         */
        var ret = 0

        var unitid = document.getElementById('unit').value // ��λ��
        var netid = document.getElementById('Mchnt').value // �̻���

        var ip = document.getElementById('ip_addr').value // IP��ַ
        var port = document.getElementById('serverport').value // ͨѶ�˿�
        var yktReader = document.getElementById('yktReader')
        ret = yktReader.YKTInit(ip, port)
        if(ret != 0){
            alert("��ʼ���ؼ�ʧ�ܡ�" + ret);
            yktEnd();
            return;
        }

        posid1 = yktReader.YKTReadSAMNO()
        if (posid1 === null || posid1 === '') {
            alert('��sam��ʧ��!')
            return
        }

        var posid = posid1 // �豸���(Pos����)
        var oprid = document.getElementById('opr').value // ����Ա����
        var info = document.getElementById('sNewCardInfo').value
        // var sNewInfo = document.getElementById('sNewInfo').value // �м��˿���Ϣ����
        try {
            ret = yktReader.SetUnit(unitid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            ret = yktReader.SetMchntid(netid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            ret = yktReader.SetPosId(posid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            ret = yktReader.SetOprId(oprid)
            if (ret !== 0) {
                alert('���ò���ʧ��!' + ret)
                return
            }

            // modified at 2015.05.11 by liqi
            var info1 = yktReader.YKTReadCard();    //����,��Ч��
            var newCardNo = "";

            // ��������
            if (check260 === null || check260 === '') {
                if (info1 === '') {
                    info1 = yktReader.YKTReadCard1() // ����,��Ч��
                    var oldCardNo = document.getElementById('oldCardNo').value // ��ȡԭ����
                    newCardNo = info1.split(',')[0] // ��ȡ�¿���

                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;

                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28){
                            //modified at 2015.12.24 by liqi
                            var infoNew = $('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //����,��Ч��

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //����,��Ч��
                                newCardNo = info1.split(",")[0]; //��ȡ����
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                                //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("��Ƭ����ʧ��,������ǩ����" + ret);
                                    }else if (ret == 326){
                                        alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("��ǰ��������ʧ��,������:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("��Ƭ����ʧ��:" + ret +",����п�������");
                                        }else{
                                            alert("��Ƭ����ʧ��:" + ret);
                                        }
                                    }
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //��ȡ����
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("��������ʧ��:" + ret);
                            return;
                        }
                    }
                } else {
                    newCardNo = info1.split(",")[0]; //��ȡ����
                    var oldCardNo = document.getElementById('oldCardNo').value // ��ȡԭ����

                    var sNewCardInfo = oldCardNo + "," + newCardNo + "," +  info + "," + cardNoFlagTemp;
                    ret = yktReader.YKTsuppleBadCard(sNewCardInfo);

                    if(ret != 0){
                        if(ret == 28){
                            //modified at 2015.12.24 by liqi
                            var infoNew = $('sInfoData').value;
                            info1 = yktReader.YKTReadCard();    //����,��Ч��

                            if(!isnull(check260)||info1 == ""){
                                info1 = yktReader.YKTReadCard1();    //����,��Ч��
                                newCardNo = info1.split(",")[0]; //��ȡ����
                                var sInfoData = newCardNo + "," + infoNew + "," + cardNoFlagTemp;

                                //����,��Ч��,�м��˱���,����, ���֤��,�ƶ��绰����,��ͥ�绰,
                                //�칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                                ret = yktReader.YKTenableCard(sInfoData);
                                if(ret != 0){
                                    yktEnd();
                                    if(ret == 6013 || ret == 156){
                                        alert("��Ƭ����ʧ��,������ǩ����" + ret);
                                    }else if (ret == 326){
                                        alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                                    }else if (ret >= 5012 && ret <=5015){
                                        alert("��ǰ��������ʧ��,������:" + ret);
                                    }else{
                                        if(!isnull(check260) && check260 == 6006){
                                            alert("��Ƭ����ʧ��:" + ret +",����п�������");
                                        }else{
                                            alert("��Ƭ����ʧ��:" + ret);
                                        }
                                    }
                                    return;
                                }
                            }else{
                                newCardNo = info1.split(",")[0]; //��ȡ����
                            }
                        }else if (ret == 326){
                            yktEnd();
                            alert("��У��λ������������ȷ�Ŀ�У��λ��" + ret);
                            return;
                        }else{
                            yktEnd();
                            alert("��������ʧ��:" + ret);
                            return;
                        }
                    }
                }
            } else {
                // ������ʧ�ܣ����·���
                if (info1 === null || info1 === '') {
                    info1 = yktReader.YKTReadCard1() // ����,��Ч��
                }

                if (info1 === null || info1 === '') {
                    yktEnd()
                    alert('������ϢΪ��')
                    return
                }

                var oldCardNo = document.getElementById('oldCardNo').value // ��ȡԭ����
                newCardNo = info1.split(',')[0] // ��ȡ�¿���


                var sNewCardInfo = oldCardNo + ',' + newCardNo + ',' + info + ',' + cardNoFlagTemp

                ret = yktReader.YKTsuppleBadCard(sNewCardInfo)

                if (ret !== 0) {
                    if (ret === 28 || ret === 39) {
                        var infoNew = document.getElementById('sInfoData').value
                        var sInfoData = newCardNo + ',' + infoNew + ',' + cardNoFlagTemp

                        // �칫�绰,email,������ַ ,�ʱ�,��������,��ѯ����,��У��
                        ret = yktReader.YKTenableCard(sInfoData)
                        if (ret !== 0) {
                            yktEnd()
                            if (ret === 6013 || ret === 156) {
                                alert('��Ƭ����ʧ��,������ǩ����' + ret)
                            } else if (ret === 326) {
                                alert('��У��λ������������ȷ�Ŀ�У��λ��' + ret)
                            } else if (ret >= 5012 && ret <= 5015) {
                                alert('��ǰ��������ʧ��,������:' + ret)
                            } else {
                                if (check260 !== null && check260 !== '' && check260 === 6006) {
                                    alert('��Ƭ����ʧ��:' + ret + ',����п�������')
                                } else {
                                    alert('��Ƭ����ʧ��:' + ret)
                                }
                            }
                            return
                        }
                    } else if (ret === 326) {
                        yktEnd()
                        alert('��У��λ������������ȷ�Ŀ�У��λ��' + ret)
                        return
                    } else {
                        yktEnd()
                        alert('��������ʧ��:' + ret)
                        return
                    }
                }
            }

            yktEnd()
        } catch (e) { alert(e.message) }

        /*
             * ���ؽӿ�---����
             */

        document.getElementById('newCardNo').value = newCardNo // ���췢���¿��Ŵ����ڻ�����ȡ
    }
    // add at 2016.04.12 ��ӿ�����260������ �ֶ�
    if (check260 !== null && check260 !== '' && check260 !== 0) {
        document.getElementById('check260').value = check260
    }
    return true
}
// �����쿨����֤�쿨
function cardBusiness110or111or4(check260,cardnoCheck,cardNoFlagTemp) {
}
// ������ȷ�Ϸ���
function cardBusiness120(check260,cardnoCheck,cardNoFlagTemp){
}
// ������ȷ�Ϸ���
function cardBusiness140(check260,cardnoCheck,cardNoFlagTemp) {
}

// ���� �ύ�����ݿ�
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
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                alert(result.content)
            } else {
                alert(result.content)
            }
        }
    });
}