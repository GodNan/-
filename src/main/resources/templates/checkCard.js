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
// 获取getCitizen
function getCitizen(citizenId) {
    alert($("#cardNo").val())
    $.ajax({
        type: "get",
        // url: "/bdpfnew/cardcheck/" + citizenId,
        url: "/cardcheck/" + citizenId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.type == "success" ) {
                $("#cardNo").val(result.data.cardNo);
            } else {
            }
        }
    });
}

// (1) 打开设备
// long OpenDC()
// 参数: 无
// 返回: <0失败, 其他为设备号

// 设备号
var DCid = -99

function openDC() {
    var GetBMACInfo = document.getElementById('GetBMACInfo')
    DCid = GetBMACInfo.OpenDC()
    if (DCid < 0) {
        alert('打开检测设备失败!')
        return
    }
}

// (2) 关闭设备
// long CloseDC(long idev)
// 参数: idev: OpenDC()取得的设备号
// 返回: <0失败,  其他成功
function closeDC() {
    var GetBMACInfo = document.getElementById('GetBMACInfo')
    if (DCid > 0) {
        var ret = GetBMACInfo.closeDC(DCid)
        if (ret < 0) {
            alert('关闭检测设备失败!')
        }
    }
}

// 【卡片检测功能】
function card_checkB() {
    if (!confirm('请确认已经将卡片放到大设备(读写卡设备)上')) {
        return
    }
    var CLActiveX = document.getElementById('CLActiveX')
    var ret = CLActiveX.InitCom() // 打开读卡器
    if (ret !== true) {
        alert('连接读卡器失败')
        return
    }
    ret = CLActiveX.ICrfproReset(1, 0) // 卡片复位
    if (ret !== 0) {
        // check
        alert('读卡失败：' + ret + ',请确认读卡设备上已放置残疾人卡')
        return
    }
    ret = CLActiveX.CloseCom() // 关闭通讯
    if (ret !== true) {
        alert('关闭通讯失败')
        return
    }
    return true
}

// 获取卡片余额
function getCardBalance() {
    // 打开设备
    openDC()
    var GetBMACInfo = document.getElementById('GetBMACInfo')
    // 透支金额
    var draw = GetBMACInfo.GetOverdraw(DCid)
    if (draw === -1) {
        alert('通讯错误!')
        closeDC()
        return
    } else if (draw === -2) {
        alert('透支金额错误')
        closeDC()
        return
    }
    // 钱包余额
    var balance = GetBMACInfo.GetBalance(DCid)
    if (balance === -1) {
        alert('通讯错误!')
        closeDC()
        return
    } else if (balance === -2) {
        alert('钱包格式错误')
        closeDC()
        return
    }

    if (draw > 0 && balance > 0) {
        alert('卡片错误：透支金额与钱包金额同时大于零!')
        closeDC()
        return
    }

    // 卡片余额
    var cardBalance = balance - draw
    var cBalance = cardBalance / 100

    // 关闭设备
    closeDC()
    return cBalance + '元 (此数据仅供参考，如有疑问请拨打公交一卡通热线进行咨询)'
}

// (5)读卡片状态
// long GetStatus(long idev)
// 参数: idev: OpenDC()取得的设备号
// 返回: -1:通讯错, 1:未启用  2:启用  3:停用

// 读取卡片状态
function getCardStatus() {
    // 打开设备
    openDC()
    var info = new Array(5)

    // 启用时提示‘此卡状态正常’，禁用时提示‘此卡已被禁用，请在系统里重新进行发卡’，停用时提示‘此卡已被停用，如有疑问请拨打公交一卡通热线进行咨询’。
    info[1] = '未启用:此卡未启用，请在系统里重新进行发卡'
    info[2] = '启用:此卡状态正常'
    info[3] = '停用:此卡已被停用，如有疑问请拨打公交一卡通热线进行咨询'

    var GetBMACInfo = document.getElementById('GetBMACInfo')
    var status = GetBMACInfo.GetStatus(DCid)

    if (status === -1) {
        alert('通讯错误!')
        closeDC()
        return
    }
    if (status > 3) {
        alert('卡片状态信息错误：' + status)
        closeDC()
        return
    }

    // 关闭设备
    closeDC()
    return info[status]
}

// (6)取卡片启用日期和失效日期
// BSTR GetDate(long idev)
// 参数: idev: OpenDC()取得的设备号
// 返回: '01'通讯错
// 字符串型卡片启用日期和卡片失效日期,  (YYYYMMDD+YYYYMMDD)
function getCardDate() {
    // 打开设备
    openDC()

    var GetBMACInfo = document.getElementById('GetBMACInfo')
    var dateInfo = GetBMACInfo.GetDate(DCid)

    if (dateInfo === '01') {
        alert('通讯错误!')
        closeDC()
        return
    }

    // 关闭设备
    closeDC()

    var activedate = dateInfo.substring(0, 8)
    var disdate = dateInfo.substring(8)

    var disyear = disdate.substring(0, 4)
    var dismonth = disdate.substring(4, 6)
    var disday = disdate.substring(6)

    var disDate = disyear + '/' + dismonth + '/' + disday

    var distime = new Date(disDate)

    if (distime < new Date()) {
        disdate += '(卡片已失效，请进行延期操作)'
    } else {
        disdate += '(卡片未失效)'
    }

    var qxInfo = new Array(2)
    qxInfo[0] = activedate
    qxInfo[1] = disdate
    return qxInfo
}


// (7)判断是否是不完整交易
// BOOL IsCompleteTransaction(long idev)
// 参数：idev: OpenDC()取得的设备号
// 返回：1: 不完整交易，即下车未啥卡
//      0: 完整交易
//	  <-1: 通讯错

function isCompleteTransaction() {
    // 打开设备
    openDC()
    var info = new Array(2)

    // 启用时提示‘此卡状态正常’，禁用时提示‘此卡已被禁用，请在系统里重新进行发卡’，停用时提示‘此卡已被停用，如有疑问请拨打公交一卡通热线进行咨询’。
    info[1] = '不完整交易，即下车未刷卡'
    info[0] = '完整交易'

    var GetBMACInfo = document.getElementById('GetBMACInfo')
    var isCompleted = GetBMACInfo.IsCompleteTransaction(DCid)

    if (isCompleted < 0) {
        alert('通讯错误!')
        closeDC()
        return
    }

    if (isCompleted > 1) {
        alert('卡片状态信息错误：' + status)
        closeDC()
        return
    }

    // 关闭设备
    closeDC()

    return info[isCompleted]
}

// (8)判断是否为最新卡
// 通过卡内读取身份证号
// 通过身份证号查询数据库中的一卡通号
function isOldCard() {
    var CLActiveX = document.getElementById('CLActiveX')
    var ret = CLActiveX.InitCom() // 打开读卡器
    var rData = CLActiveX.ICReadPersonInfo() // 读接口
    if (rData === null || rData === '') {
        alert('卡片未写入个人信息')
        return
    }
    // console.log('【是否旧卡】 rData  :' + rData)
    var disId = rData.split(',')[4] // 取身份证号
    // console.log('【是否旧卡】 卡内身份证号  :' + disId)
    var rCardNo = CLActiveX.ICReadCardNum() // 读取卡内一卡通号
    // console.log('【是否旧卡】 卡片内一卡通号  :' + rCardNo)
    ret = CLActiveX.CloseCom() // 关闭通讯
    if (ret) {
        // 通过身份证号从数据库查询一卡通号
        getCitizen(disId)
        var cardNo =  $("#cardNo").val()
        // console.log('【是否旧卡】 系统内一卡通号  :' + cardNo)
        var info = new Array(2)
        info[0] = '是(旧卡已作废，请使用新卡)'
        info[1] = '否'
        info[2] = '卡内或系统内一卡通号为空，无法确定。'
        if (cardNo !== null && cardNo !== '' && rCardNo !== null && cardNo !== '') {
            if (cardNo.indexOf(rCardNo) < 0) {
                return info[0]
            } else {
                return info[1]
            }
        } else {
            return info[2]
        }
    } else {
        alert('关闭通讯失败')
        return
    }
}