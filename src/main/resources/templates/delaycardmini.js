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

    if (ret !== 0) {
        alert('��ʼ���ؼ�ʧ�ܡ�' + ret)
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        return
    }

    // ��sam����
    posid1 = yktReader.YKTReadSAMNO()

    if (posid1 === null || posid1 === '') {
        alert('��sam��ʧ��!')
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        return
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
        if(ret == '264'){
            alert("unitid=" + unitid + ";netid"+netid+";ip="+ip+";port="+port + ";posid1=" + posid1)
        }
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        return
    } else {
        alert('ǩ���ɹ�!' + ret)
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        // add lmn ����ǩ����ť
        $("#bkcheckin").css("display","none");
        return
    }
}


function save_delayCard() {
    if (!confirm('��ȷ�Ͽ�Ƭ�Ѿ�����������С�豸�ϲ����Ѿ�ǩ��')) {
        return
    }
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
    if (ret !== 0) {
        // yktEnd()
        if (ret === 156 || ret === 6013) {
            alert('������ǩ��')

        } else {
            alert('��ʼ���ؼ�ʧ�ܡ�' + ret)
        }
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        return
    }

    posid1 = yktReader.YKTReadSAMNO()
    if (posid1 === null || posid1 === '') {
        alert('��sam��ʧ��!')
        return
    }

    var posid = posid1 // �豸���(Pos����)
    var oprid = document.getElementById('opr').value // ����Ա����

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

        var info1 = yktReader.YKTReadCard() // ����,��Ч��
        var cardNo = ''

        if (info1 != null && info1 != '') {
            cardNo = info1.split(',')[0] // ��ȡ����
        } else {
            info1 = yktReader.YKTReadCard1() // ����,��Ч��
            alert("����,��Ч��2:" + info1)
            if (info1 != null && info1 != '') {
                cardNo = info1.split(',')[0] // ��ȡ����
            } else {
                // yktEnd()
                alert('����ʧ�ܣ�һ��ͨ��Ϊ��')
                ret = yktReader.yktend()
                if (ret !== 0) {
                    alert('�ؼ��ر�ʧ��' + ret)
                    alert('��ر���������²���')
                }
                return
            }
        }
        // yktEnd()
        ret = yktReader.yktend()
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
    // add at 2016.05.04 by liqi
    // ������ʱ��֤һ��ͨ��һ��
    var cardnoCheck = document.getElementById('cardnoCheck').value
    // �Ƚ�һ��ͨ��
    // ��һ�²���������
    if (cardnoCheck !== null && cardnoCheck !== '') {
        if (cardNo !== cardnoCheck) {
            // console.log('cardNo:' + cardNo)
            // console.log('cardnoCheck:' + cardnoCheck)
            alert('�˿�Ϊ�ɿ��޷�����!cardNo=' + cardNo + ";cardnoCheck=" +cardnoCheck)
            return
        }
    }

    document.getElementById('newCardNo').value = cardNo // ��һ�η������¿��ŵ��ڴ����ڻ��������Ŀ���

    // һ��ͨ��һ�º��ȡ
    return commit_delayCard()
}

function commit_delayCard() {
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
    // alert("yktinit>>>retֵ="+ret)
    if (ret !== 0) {
        alert('��ʼ���ؼ�ʧ�ܡ�' + ret)
        // yktEnd()
        ret = yktReader.yktend()
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        return
    }

    posid1 = yktReader.YKTReadSAMNO()
    if (posid1 === null || posid1 === '') {
        alert('��sam��ʧ��!')
        return
    }

    var posid = posid1 // �豸���(Pos����)
    var oprid = document.getElementById('opr').value // ����Ա����
    var info = document.getElementById('sYearInfo').value

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

        ret = yktReader.YktpostphoneCard(info)
        // alert("YktpostphoneCard>>>>retֵ="+ret)
        if (ret !== 0) {
            // modified at 2016.04.12
            // ������ʧ��
            if (ret !== 260 && ret !== 6006) {
                // yktEnd()
                ret = yktReader.yktend()
                // alert("yktend>>>>retֵ="+ret)
                if (ret !== 0) {
                    alert('�ؼ��ر�ʧ��' + ret)
                    alert('��ر���������²���')
                }
                if (ret >= 5012 && ret <= 5015) {
                    alert('��ǰ��������ʧ��,������:' + ret)
                } else {
                    alert('������ʧ��!' + ret)
                }
                return
            } else {
                alert('������ʧ�ܣ�' + ret) // ��20181217�޸ģ�����260�ǲ������·�������Ϊ�󲿷ֶ������״η���δ�ɹ���
                alert('��ر���������²��������ҽ����ÿ����½���ǩ����')

                // 260��δ�����µĽ������κ�  --���·���
                // yktEnd()
                ret = yktReader.yktend()
                // alert("yktend>>>>retֵ="+ret)
                if (ret !== 0) {
                    alert('�ؼ��ر�ʧ��' + ret)
                    alert('��ر���������²���')
                }
                // TODO:
                // ��������260���������
                // 260,6006����ͬһ�������������߼���ͨ
                // 260���·�����6006���·���ʧ�ܺ���ʾ���п�������
                // alert('�״η���δ�ɹ����������·�����')   ��20181217�޸ģ�����260�ǲ������·�������Ϊ�󲿷ֶ������״η���δ�ɹ���
                // confirm_save_givedCard260(ret)   ��20181217�޸ģ�����260�ǲ������·�������Ϊ�󲿷ֶ������״η���δ�ɹ���
                return
            }
        }
        // yktEnd()
        ret = yktReader.yktend()
        // alert("yktend>>>>retֵ="+ret)
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
    } catch (e) {
        // yktEnd()
        ret = yktReader.yktend()
        // alert("yktend>>>>retֵ="+ret)
        if (ret !== 0) {
            alert('�ؼ��ر�ʧ��' + ret)
            alert('��ر���������²���')
        }
        alert(e.message)
    }

    return true
}

// ��ȡsYearInfo
function sYearInfo(requestIdCard) {
    $.ajax({
        type: "get",
        url: "/bdpfnew/cardcheck/sYearInfo/" + requestIdCard,
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
        async:false,
        headers :{'Content-Type':'application/json;charset=utf8','JSESSIONID':getParamFromUrl('tockionId')},
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                $("#cardnoCheck").val(result.data.cardNo);
            } else {
            }
        }
    });
}

// ������ �ύ�����ݿ�
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
        success : function(result) {//�������ݸ��ݽ��������Ӧ�Ĵ���
            if ( result.type == "success" ) {
                alert(result.content);
                $("#sYearInfo").val(result.data);
            } else {
            }
        }
    });
}