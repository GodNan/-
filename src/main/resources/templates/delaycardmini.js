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

    if (ret !== 0) {
        alert('初始化控件失败。' + ret)
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        return
    }

    // 读sam卡号
    posid1 = yktReader.YKTReadSAMNO()

    if (posid1 === null || posid1 === '') {
        alert('读sam卡失败!')
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        return
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
        if(ret == '264'){
            alert("unitid=" + unitid + ";netid"+netid+";ip="+ip+";port="+port + ";posid1=" + posid1)
        }
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        return
    } else {
        alert('签到成功!' + ret)
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        // add lmn 隐藏签到按钮
        $("#bkcheckin").css("display","none");
        return
    }
}


function save_delayCard() {
    if (!confirm('请确认卡片已经放在正方形小设备上并且已经签到')) {
        return
    }
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
    if (ret !== 0) {
        // yktEnd()
        if (ret === 156 || ret === 6013) {
            alert('请重新签到')

        } else {
            alert('初始化控件失败。' + ret)
        }
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        return
    }

    posid1 = yktReader.YKTReadSAMNO()
    if (posid1 === null || posid1 === '') {
        alert('读sam卡失败!')
        return
    }

    var posid = posid1 // 设备编号(Pos机号)
    var oprid = document.getElementById('opr').value // 操作员代码

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

        var info1 = yktReader.YKTReadCard() // 卡号,有效期
        var cardNo = ''

        if (info1 != null && info1 != '') {
            cardNo = info1.split(',')[0] // 获取卡号
        } else {
            info1 = yktReader.YKTReadCard1() // 卡号,有效期
            alert("卡号,有效期2:" + info1)
            if (info1 != null && info1 != '') {
                cardNo = info1.split(',')[0] // 获取卡号
            } else {
                // yktEnd()
                alert('读卡失败，一卡通号为空')
                ret = yktReader.yktend()
                if (ret !== 0) {
                    alert('控件关闭失败' + ret)
                    alert('请关闭浏览器重新操作')
                }
                return
            }
        }
        // yktEnd()
        ret = yktReader.yktend()
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
    // add at 2016.05.04 by liqi
    // 卡延期时验证一卡通号一致
    var cardnoCheck = document.getElementById('cardnoCheck').value
    // 比较一卡通号
    // 不一致不允许卡延期
    if (cardnoCheck !== null && cardnoCheck !== '') {
        if (cardNo !== cardnoCheck) {
            // console.log('cardNo:' + cardNo)
            // console.log('cardnoCheck:' + cardnoCheck)
            alert('此卡为旧卡无法延期!cardNo=' + cardNo + ";cardnoCheck=" +cardnoCheck)
            return
        }
    }

    document.getElementById('newCardNo').value = cardNo // 第一次发卡，新卡号等于从延期机器读出的卡号

    // 一卡通号一致后调取
    return commit_delayCard()
}

function commit_delayCard() {
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
    // alert("yktinit>>>ret值="+ret)
    if (ret !== 0) {
        alert('初始化控件失败。' + ret)
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        return
    }

    posid1 = yktReader.YKTReadSAMNO()
    if (posid1 === null || posid1 === '') {
        alert('读sam卡失败!')
        return
    }

    var posid = posid1 // 设备编号(Pos机号)
    var oprid = document.getElementById('opr').value // 操作员代码
    var info = document.getElementById('sYearInfo').value

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

        ret = yktReader.YktpostphoneCard(info)
        // alert("YktpostphoneCard>>>>ret值="+ret)
        if (ret !== 0) {
            // modified at 2016.04.12
            // 卡延期失败
            if (ret !== 260 && ret !== 6006) {
                // yktEnd()
                ret = yktReader.yktend()
                // alert("yktend>>>>ret值="+ret)
                if (ret !== 0) {
                    alert('控件关闭失败' + ret)
                    alert('请关闭浏览器重新操作')
                }
                if (ret >= 5012 && ret <= 5015) {
                    alert('当前网络连接失败,请重试:' + ret)
                } else {
                    alert('卡延期失败!' + ret)
                }
                return
            } else {
                alert('卡延期失败：' + ret) // 【20181217修改：报错260是不再重新发卡，因为大部分都不是首次发卡未成功】
                alert('请关闭浏览器重新操作，并且将卡拿开重新进行签到。')

                // 260：未申请新的交易批次号  --重新发卡
                // yktEnd()
                ret = yktReader.yktend()
                // alert("yktend>>>>ret值="+ret)
                if (ret !== 0) {
                    alert('控件关闭失败' + ret)
                    alert('请关闭浏览器重新操作')
                }
                // TODO:
                // 处理卡延期260报错的问题
                // 260,6006共用同一个处理方法，仅逻辑不通
                // 260重新发卡，6006重新发卡失败后提示进行卡残损换新
                // alert('首次发卡未成功，进行重新发卡！')   【20181217修改：报错260是不再重新发卡，因为大部分都不是首次发卡未成功】
                // confirm_save_givedCard260(ret)   【20181217修改：报错260是不再重新发卡，因为大部分都不是首次发卡未成功】
                return
            }
        }
        // yktEnd()
        ret = yktReader.yktend()
        // alert("yktend>>>>ret值="+ret)
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
    } catch (e) {
        // yktEnd()
        ret = yktReader.yktend()
        // alert("yktend>>>>ret值="+ret)
        if (ret !== 0) {
            alert('控件关闭失败' + ret)
            alert('请关闭浏览器重新操作')
        }
        alert(e.message)
    }

    return true
}

// 获取sYearInfo
function sYearInfo(requestIdCard) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sYearInfo/" + requestIdCard,
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
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                $("#cardnoCheck").val(result.data.cardNo);
            } else {
            }
        }
    });
}

// 卡延期 提交到数据库
function delayCard() {
    var param = {};
    param.requestIdCard = $("#requestIdCard").val();
    param.check260 = $("#check260").val();
    param.cardNoFlagT = "";
    param.newCardNo = $("#newCardNo").val();
    $.ajax({
        type: "put",
        url: "/bdpfnew/cardDelay",
        // async:false,
        dateType:"JSON",
        data : JSON.stringify(param),
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                alert(result.content);
                $("#sYearInfo").val(result.data);
            } else {
            }
        }
    });
}