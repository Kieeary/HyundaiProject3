var btnConfirmText = '확인';
var btnCancleText = '취소';
var btnCloseText = '닫기';

/*
layer popup 형식의 alert 창
========================[example code]===========================
layerAlert('메세지를 입력하세요');
var lc = new layerAlert('입력하신 정보가 변경완료 되었습니다.');
lc.confirmAction = function(){
 //alert화면 실행후 후속 처리 부분 추가
 location.href="/ko/mypage/order/myorders";
};
=============================================================
*/

function layerAlert(msg, refresh) {
    btnConfirmText = '확인';
    var t = this;
    this.confirmAction = function () {
        //console.log('함수를 재정의 해주세요!');
    };

    var alertTag = '';
    alertTag += '<div class="dvp_container">';
    alertTag +=
        '	<div class="dvp_close_btn b-close" id="alertCloseDiv"><span>' +
        btnCloseText +
        '</span></div>';
    alertTag += '	<h1 class="dvp_header"></h1>';
    alertTag += '	<div class="dvp_content">';
    alertTag += '		<div class="desc_box">' + msg + '</div>';
    alertTag += '	</div>';
    alertTag += '	<div class="btn_wrap">';
    alertTag +=
        '		<button class="btn btn_bg_b btn-alert-confirm">' +
        btnConfirmText +
        '</button>';
    alertTag += '	</div>';
    alertTag += '</div>';

    $('#popAlertConfirmDiv').html(alertTag);

    $('.divpop_wrap_dev.pop_alert_confirm').bPopup({
        follow: [false, false],
        escClose: false,
        modalClose: false,
        opacity: 0.3,
        positionStyle: 'fixed',
        onClose: function () {
            holdBodyHide();
            $('#popAlertConfirmDiv').html('');
        },
    });
    holdBodyShow();

    $('#popAlertConfirmDiv')
        .find('.btn-alert-confirm')
        .click(function () {
            if (refresh == true) location.reload();
            $('#alertCloseDiv').click();
            t.confirmAction();
        });
};


/*
layer popup 형식의 alert 창
========================[example code]===========================

var lc = new layerConfirm('메세지를 입력하세요', '확인버튼', '취소버튼');
lc.confirmAction = function(){
  console.log('재정의한 함수입니다.');
};

=============================================================
*/
function layerConfirm(msg, confirmMsg, cancleMsg) {

    var t = this;
    this.confirmAction = function () {
        //console.log('함수를 재정의 해주세요!');
    };
    this.cancleAction = function () {
        //console.log('함수를 재정의 해주세요!');
    };

    if (confirmMsg != '') {
        btnConfirmText = confirmMsg;
    } else {
        btnConfirmText = '확인';
    }
    if (cancleMsg != '') {
        btnCancleText = cancleMsg;
    } else {
        btnCancleText = '취소';
    }

    var confirmTag = '';
    confirmTag += '<div class="dvp_container">';
    confirmTag +=
        '	<div class="dvp_close_btn b-close" id="confirmCloseDiv"><span>' +
        btnCloseText +
        '</span></div>';
    confirmTag += '	<div class="dvp_content">';
    confirmTag += '		<div class="desc_box">' + msg + '</div>';
    confirmTag += '	</div>';
    confirmTag += '	<div class="btn_wrap btns">';
    confirmTag +=
        '		<button class="btn btn_bd_g btn-alert-close">' +
        btnCancleText +
        '</button>';
    confirmTag +=
        '		<button class="btn btn_bg_b btn-alert-confirm">' +
        btnConfirmText +
        '</button>';
    confirmTag += '	</div>';
    confirmTag += '</div>';

    $('#popAlertConfirmDiv').html(confirmTag);

    $('.divpop_wrap_dev.pop_alert_confirm').bPopup({
        follow: [false, false],
        escClose: false,
        modalClose: false,
        opacity: 0.3,
        positionStyle: 'fixed',
        onClose: function () {
            holdBodyHide();
            $('#popAlertConfirmDiv').html('');
        },
    });
    holdBodyShow();

    $('#popAlertConfirmDiv')
        .find('.btn-alert-close')
        .click(function () {
            $('#confirmCloseDiv').click();
            t.cancleAction();
        });

    $('#popAlertConfirmDiv')
        .find('.btn-alert-confirm')
        .click(function () {
            $('#confirmCloseDiv').click();
            t.confirmAction();
        });
};