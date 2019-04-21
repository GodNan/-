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
// ��ȡgetCitizen
function getCitizen(citizenId) {
    alert($("#cardNo").val())
    $.ajax({
        type: "get",
        // url: "/bdpfnew/cardcheck/" + citizenId,
        url: "/cardcheck/" + citizenId,
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                $("#cardNo").val(result.data.cardNo);
            } else {
            }
        }
    });
}

// (1) ���豸
// long OpenDC()
// ����: ��
// ����: <0ʧ��, ����Ϊ�豸��

// �豸��
var DCid = -99

function openDC() {
    var GetBMACInfo = document.getElementById('GetBMACInfo')
    DCid = GetBMACInfo.OpenDC()
    if (DCid < 0) {
        alert('�򿪼���豸ʧ��!')
        return
    }
}

// (2) �ر��豸
// long CloseDC(long idev)
// ����: idev: OpenDC()ȡ�õ��豸��
// ����: <0ʧ��,  �����ɹ�
function closeDC() {
    var GetBMACInfo = document.getElementById('GetBMACInfo')
    if (DCid > 0) {
        var ret = GetBMACInfo.closeDC(DCid)
        if (ret < 0) {
            alert('�رռ���豸ʧ��!')
        }
    }
}

// ����Ƭ��⹦�ܡ�
function card_checkB() {
    if (!confirm('��ȷ���Ѿ�����Ƭ�ŵ����豸(��д���豸)��')) {
        return
    }
    var CLActiveX = document.getElementById('CLActiveX')
    var ret = CLActiveX.InitCom() // �򿪶�����
    if (ret !== true) {
        alert('���Ӷ�����ʧ��')
        return
    }
    ret = CLActiveX.ICrfproReset(1, 0) // ��Ƭ��λ
    if (ret !== 0) {
        // check
        alert('����ʧ�ܣ�' + ret + ',��ȷ�϶����豸���ѷ��òм��˿�')
        return
    }
    ret = CLActiveX.CloseCom() // �ر�ͨѶ
    if (ret !== true) {
        alert('�ر�ͨѶʧ��')
        return
    }
    return true
}

// ��ȡ��Ƭ���
function getCardBalance() {
    // ���豸
    openDC()
    var GetBMACInfo = document.getElementById('GetBMACInfo')
    // ͸֧���
    var draw = GetBMACInfo.GetOverdraw(DCid)
    if (draw === -1) {
        alert('ͨѶ����!')
        closeDC()
        return
    } else if (draw === -2) {
        alert('͸֧������')
        closeDC()
        return
    }
    // Ǯ�����
    var balance = GetBMACInfo.GetBalance(DCid)
    if (balance === -1) {
        alert('ͨѶ����!')
        closeDC()
        return
    } else if (balance === -2) {
        alert('Ǯ����ʽ����')
        closeDC()
        return
    }

    if (draw > 0 && balance > 0) {
        alert('��Ƭ����͸֧�����Ǯ�����ͬʱ������!')
        closeDC()
        return
    }

    // ��Ƭ���
    var cardBalance = balance - draw
    var cBalance = cardBalance / 100

    // �ر��豸
    closeDC()
    return cBalance + 'Ԫ (�����ݽ����ο������������벦�򹫽�һ��ͨ���߽�����ѯ)'
}

// (5)����Ƭ״̬
// long GetStatus(long idev)
// ����: idev: OpenDC()ȡ�õ��豸��
// ����: -1:ͨѶ��, 1:δ����  2:����  3:ͣ��

// ��ȡ��Ƭ״̬
function getCardStatus() {
    // ���豸
    openDC()
    var info = new Array(5)

    // ����ʱ��ʾ���˿�״̬������������ʱ��ʾ���˿��ѱ����ã�����ϵͳ�����½��з�������ͣ��ʱ��ʾ���˿��ѱ�ͣ�ã����������벦�򹫽�һ��ͨ���߽�����ѯ����
    info[1] = 'δ����:�˿�δ���ã�����ϵͳ�����½��з���'
    info[2] = '����:�˿�״̬����'
    info[3] = 'ͣ��:�˿��ѱ�ͣ�ã����������벦�򹫽�һ��ͨ���߽�����ѯ'

    var GetBMACInfo = document.getElementById('GetBMACInfo')
    var status = GetBMACInfo.GetStatus(DCid)

    if (status === -1) {
        alert('ͨѶ����!')
        closeDC()
        return
    }
    if (status > 3) {
        alert('��Ƭ״̬��Ϣ����' + status)
        closeDC()
        return
    }

    // �ر��豸
    closeDC()
    return info[status]
}

// (6)ȡ��Ƭ�������ں�ʧЧ����
// BSTR GetDate(long idev)
// ����: idev: OpenDC()ȡ�õ��豸��
// ����: '01'ͨѶ��
// �ַ����Ϳ�Ƭ�������ںͿ�ƬʧЧ����,  (YYYYMMDD+YYYYMMDD)
function getCardDate() {
    // ���豸
    openDC()

    var GetBMACInfo = document.getElementById('GetBMACInfo')
    var dateInfo = GetBMACInfo.GetDate(DCid)

    if (dateInfo === '01') {
        alert('ͨѶ����!')
        closeDC()
        return
    }

    // �ر��豸
    closeDC()

    var activedate = dateInfo.substring(0, 8)
    var disdate = dateInfo.substring(8)

    var disyear = disdate.substring(0, 4)
    var dismonth = disdate.substring(4, 6)
    var disday = disdate.substring(6)

    var disDate = disyear + '/' + dismonth + '/' + disday

    var distime = new Date(disDate)

    if (distime < new Date()) {
        disdate += '(��Ƭ��ʧЧ����������ڲ���)'
    } else {
        disdate += '(��ƬδʧЧ)'
    }

    var qxInfo = new Array(2)
    qxInfo[0] = activedate
    qxInfo[1] = disdate
    return qxInfo
}


// (7)�ж��Ƿ��ǲ���������
// BOOL IsCompleteTransaction(long idev)
// ������idev: OpenDC()ȡ�õ��豸��
// ���أ�1: ���������ף����³�δɶ��
//      0: ��������
//	  <-1: ͨѶ��

function isCompleteTransaction() {
    // ���豸
    openDC()
    var info = new Array(2)

    // ����ʱ��ʾ���˿�״̬������������ʱ��ʾ���˿��ѱ����ã�����ϵͳ�����½��з�������ͣ��ʱ��ʾ���˿��ѱ�ͣ�ã����������벦�򹫽�һ��ͨ���߽�����ѯ����
    info[1] = '���������ף����³�δˢ��'
    info[0] = '��������'

    var GetBMACInfo = document.getElementById('GetBMACInfo')
    var isCompleted = GetBMACInfo.IsCompleteTransaction(DCid)

    if (isCompleted < 0) {
        alert('ͨѶ����!')
        closeDC()
        return
    }

    if (isCompleted > 1) {
        alert('��Ƭ״̬��Ϣ����' + status)
        closeDC()
        return
    }

    // �ر��豸
    closeDC()

    return info[isCompleted]
}

// (8)�ж��Ƿ�Ϊ���¿�
// ͨ�����ڶ�ȡ���֤��
// ͨ�����֤�Ų�ѯ���ݿ��е�һ��ͨ��
function isOldCard() {
    var CLActiveX = document.getElementById('CLActiveX')
    var ret = CLActiveX.InitCom() // �򿪶�����
    var rData = CLActiveX.ICReadPersonInfo() // ���ӿ�
    if (rData === null || rData === '') {
        alert('��Ƭδд�������Ϣ')
        return
    }
    // console.log('���Ƿ�ɿ��� rData  :' + rData)
    var disId = rData.split(',')[4] // ȡ���֤��
    // console.log('���Ƿ�ɿ��� �������֤��  :' + disId)
    var rCardNo = CLActiveX.ICReadCardNum() // ��ȡ����һ��ͨ��
    // console.log('���Ƿ�ɿ��� ��Ƭ��һ��ͨ��  :' + rCardNo)
    ret = CLActiveX.CloseCom() // �ر�ͨѶ
    if (ret) {
        // ͨ�����֤�Ŵ����ݿ��ѯһ��ͨ��
        getCitizen(disId)
        var cardNo =  $("#cardNo").val()
        // console.log('���Ƿ�ɿ��� ϵͳ��һ��ͨ��  :' + cardNo)
        var info = new Array(2)
        info[0] = '��(�ɿ������ϣ���ʹ���¿�)'
        info[1] = '��'
        info[2] = '���ڻ�ϵͳ��һ��ͨ��Ϊ�գ��޷�ȷ����'
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
        alert('�ر�ͨѶʧ��')
        return
    }
}