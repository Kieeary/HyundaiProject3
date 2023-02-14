let template = `
<style rel="stylesheet" type="text/css">
body {
  line-height: inherit;
}
.btn_wrap .btn {
  margin-left: 0;
}
.swiper-slide {
  font-size: 0;
}
</style>
<script type="text/javascript">
$(document).ready(function () {
  $('body').addClass('hold_body');
  if (
    handsomePageId == 'mobileMain201903Page' &&
    handsomePageId == 'mobile-homepage' &&
    goToMySmartFilterYn == 'Y'
  ) {
    goToMySmartFilterYn = 'N';
    setTimeout(function () {
      $('#navTab03').click();
      setTimeout(function () {
        $('#mySmartFilterRing').click();
      }, 1);
    }, 1);
  } else if (
    handsomePageId == 'productList' ||
    handsomePageId == 'outletMainPage' ||
    handsomePageId == 'edShopMainPage'
  ) {
    if (gnbType == 'ca' || gnbType == 'ou' || gnbType == 'ed') {
      setTimeout(function () {
        $('#navTab02').click();
      }, 1);
    }
    gnbType = 'br';
  } else if ('mainYoursPage' == handsomePageId) {
    if ('ca' == mainYourPageParam) {
      setTimeout(function () {
        $('#navTab02').click();
      }, 0);
      mainYourPageParam = 'br';
    }
  }
});
function navShopClose() {
  $('body').removeClass('hold_body');
}

function moveBrandLookBook(brandCode) {
  var url = '/ko/magazine/lookbook';
  var $form = $('<form></form>');
  $form.attr('action', url);
  $form.attr('method', 'post');
  $form.append(
    "<input type='hidden' name='CSRFToken' value='b2d55a21-4fa7-43dd-8fe0-5651039a697e' />"
  );
  $form.append(
    "<input type='hidden' name='paramBrandCode' value='" +
      brandCode +
      "' />"
  );
  $form.appendTo('body');

  $form.submit();
}
</script>
<!-- Contents -->
<div class="hand_footer_gnb_wrap hand_footer_gnb_wrap2002">
<div class="header">
  <h2>샵</h2>
  <ul class="ui_tab red_border_tab active" >
    <li class="on" style="width:50%"><a href="#tab_01" id="navTab01" >브랜드</a></li>
    <li class=""><a href="#tab_02" id="navTab02">카테고리</a></li>

    <li class="fcs" style="transform: translateX(0px); width:50%"></li>
  </ul>

</div>
<div class="container">
  <div class="content">
    <div class="shop_cont">
      <div class="tab_cont">
        <!-- 브랜드 -->
        <script type="text/javascript">
          $(document).ready(function () {
            //scrollFix200217_3();//탑메뉴 스크롤 이벤트
            //scrollEvent2002_lnb();//탐메뉴 클릭 이벤트
            lnbBrandTab(); // 브랜드 탭메뉴 클릭
            if (
              $('.brd_shop_cate_wrap2002_single').find('.like.on')
                .length > 0
            ) {
              $('.my_heart_flag2002').show();
            } else {
              $('.flag_fixed_wrap2002').css('visibility', 'hidden');
            }
            //$(".brd_shop_cate_wrap2002_single > ul:last").addClass("last_ul2002");

            var ua = navigator.userAgent.toLowerCase();
            if (
              ua.indexOf('iphone') > -1 ||
              ua.indexOf('ipad') > -1 ||
              ua.indexOf('ipod') > -1
            ) {
              $('.hand_footer_gnb_wrap').css(
                'height',
                $('.hand_footer_gnb_wrap').height() + 3
              );
            }
            //$('.hand_footer_gnb_wrap2002 .shop_cont .shop_list_brand.last_ul2002').css('height', $(window).height() - 157);
          });
          function scrollFix200217_3() {
            $('.hand_footer_gnb_wrap2002 .container').scroll(
              function () {
                var y = $(
                  '.hand_footer_gnb_wrap2002 .container'
                ).scrollTop();
                var $fixedLayer = $('.brd_navigator_bar2002_wrap');
                var naviH = $fixedLayer.height();
                var $append_cnt2 = 0;
                if (y >= 0) {
                  if (
                    $append_cnt2 == 0 &&
                    $fixedLayer.find('.fcs2').length == 0
                  ) {
                    $fixedLayer
                      .find('ul')
                      .append('<li class="fcs2"></li>');
                    $append_cnt2 = 1;
                  }
                  $fixedLayer.find('.default_fcs2').hide();
                  var chLength =
                    $fixedLayer.find('.scr_btn200217').length;
                  var i = 1;
                  var brd_index = 0;

                  if ($('#brandCountryCd').val() == 'ko') {
                    if (
                      163 >= $('#brd_cate_' + i).offset().top &&
                      163 < $('#brd_cate_' + (i + 1)).offset().top
                    ) {
                      brd_index = i - 1;
                      translateByIndex(brd_index, $fixedLayer);
                    } else if (
                      163 >= $('#brd_cate_' + (i + 1)).offset().top &&
                      163 < $('#brd_cate_' + (i + 2)).offset().top
                    ) {
                      brd_index = i;
                      translateByIndex(brd_index, $fixedLayer);
                    } else if (
                      163 >= $('#brd_cate_' + (i + 2)).offset().top &&
                      163 < $('#brd_cate_' + (i + 3)).offset().top
                    ) {
                      brd_index = i + 1;
                      translateByIndex(brd_index, $fixedLayer);
                    } else if (
                      163 >= $('#brd_cate_' + (i + 3)).offset().top
                    ) {
                      brd_index = i + 2;
                      translateByIndex(brd_index, $fixedLayer);
                    }
                  } else {
                    if (
                      163 >= $('#brd_cate_' + i).offset().top &&
                      163 < $('#brd_cate_' + (i + 1)).offset().top
                    ) {
                      brd_index = i - 1;
                      translateByIndex(brd_index, $fixedLayer);
                    } else if (
                      163 >= $('#brd_cate_' + (i + 1)).offset().top &&
                      163 < $('#brd_cate_' + (i + 2)).offset().top
                    ) {
                      brd_index = i;
                      translateByIndex(brd_index, $fixedLayer);
                    } else if (
                      163 >= $('#brd_cate_' + (i + 2)).offset().top
                    ) {
                      brd_index = i + 1;
                      translateByIndex(brd_index, $fixedLayer);
                    }
                  }
                }
              }
            );
          }
          function scrollEvent2002_lnb() {
            var $append_cnt2 = 0;
            $('.scr_btn200217 a').on('click', function (event) {
              var $fixedLayer = $('.brd_navigator_bar2002_wrap');
              if (
                $append_cnt2 == 0 &&
                $fixedLayer.find('.fcs2').length == 0
              ) {
                $fixedLayer.find('ul').append('<li class="fcs2"></li>');
                $append_cnt2 = 1;
              }
              event.preventDefault();
              var $list1_H = $('#brd_cate_1').outerHeight() + 12,
                $list2_H = $('#brd_cate_2').outerHeight() + 12,
                $list3_H = $('#brd_cate_3').outerHeight() + 12,
                $list4_H = $('#brd_cate_4').outerHeight() + 12;
              var _total_height = 0;
              if (this.hash == '#brd_cate_1') {
                _total_height = 0;
                scrollTopAnimate(_total_height);
              } else if (this.hash == '#brd_cate_2') {
                _total_height = $list1_H;
                scrollTopAnimate(_total_height);
              } else if (this.hash == '#brd_cate_3') {
                _total_height = $list1_H + $list2_H;
                scrollTopAnimate(_total_height);
              } else if (
                this.hash == '#brd_cate_4' &&
                $('#brandCountryCd').val() == 'ko'
              ) {
                _total_height = $list1_H + $list2_H + $list3_H;
                scrollTopAnimate(_total_height);
              }
            });
          }
          function scrollTopAnimate(_total_height) {
            $('.hand_footer_gnb_wrap2002 .container').animate(
              {
                scrollTop: _total_height,
              },
              800,
              'swing'
            );
          }
          function scrollTopAnimate_s0(_total_height) {
            $('.hand_footer_gnb_wrap2002 .container').animate(
              {
                scrollTop: _total_height,
              },
              0,
              'swing'
            );
          }
          function translateByIndex(brd_index, $fixedLayer) {
            var _fixedLayer = $fixedLayer;
            _fixedLayer
              .find('li')
              .eq(brd_index)
              .siblings('li')
              .removeClass('on');
            _fixedLayer.find('li').eq(brd_index).addClass('on');
            _fixedLayer.find('.fcs2').css({
              transform:
                'translateX(' +
                _fixedLayer.find('li').eq(brd_index).position().left +
                'px)',
            });
          }

          var navParentBrandCode = '';
          var navBrandCode = '';
          var navBrandName = '';
          var navSortIndex = '';

          function applyNavBrand(id, tabType) {
            // tabType : single, all
            var valueSplitArray = $('#' + id)
              .val()
              .split('#');

            $('#' + id + '_like').removeClass('on');
            var lc = new layerConfirm(
              '로그인 후 당신만의 브랜드 즐겨찾기를 <br /> 완성하세요.',
              '',
              ''
            );
            lc.cancleAction = function () {
              //취소 호출 펑션
              $('#' + id + '_like').removeClass('on');
              return;
            };

            lc.confirmAction = function () {
              //확인 호출 펑션
              location.href = '/ko/member/login';
            };
            $('#' + id + '_like').removeClass('on');
          }

          function replaceNavBrandSplitStr(
            str1,
            str2,
            str3,
            str4,
            str5
          ) {
            var replaceStr2 = '';
            var replaceStr3 = '';
            var replaceStr4 = '';
            var replaceStr5 = '';
            var replaceStrArr2 = str2.split(',');
            var replaceStrArr3 = str3.split(',');
            var replaceStrArr4 = str4.split(',');
            var replaceStrArr5 = str5.split(',');
            replaceStrArr2.reverse();
            replaceStrArr3.reverse();
            replaceStrArr4.reverse();
            replaceStrArr5.reverse();

            for (var i = 0; i < replaceStrArr2.length; i++) {
              var checkStr =
                replaceStrArr2[i] + '_' + replaceStrArr3[i];
              //var checkStr = replaceStrArr2[i]+"_"+replaceStrArr3[i]+"_"+replaceStrArr4[i];
              //var checkStr = replaceStrArr3[i];
              if (checkStr != str1) {
                if (replaceStr2 == '') {
                  replaceStr2 = replaceStrArr2[i];
                  replaceStr3 = replaceStrArr3[i];
                  replaceStr4 = replaceStrArr4[i];
                } else {
                  replaceStr2 = replaceStrArr2[i] + ',' + replaceStr2;
                  replaceStr3 = replaceStrArr3[i] + ',' + replaceStr3;
                  replaceStr4 = replaceStrArr4[i] + ',' + replaceStr4;
                }
              }

              if (i < replaceStrArr2.length - 1) {
                if (replaceStr5 == '') {
                  replaceStr5 = replaceStrArr5[i];
                } else {
                  replaceStr5 = replaceStrArr5[i] + ',' + replaceStr5;
                }
              }
            }
            navParentBrandCode = replaceStr2;
            navBrandCode = replaceStr3;
            navBrandName = replaceStr4;
            navSortIndex = replaceStr5;
          }

          function gaLike(brandName) {
            GA_Event('공통_브랜드', '좋아요', brandName);
          }

          function gaNotLike(brandName, gubun) {
            var v_action = '좋아요 취소';
            if (gubun != 'heart') {
              v_action = '하단 좋아요 취소';
            }
            GA_Event('공통_브랜드', v_action, brandName);
          }

          //브랜드 탭메뉴 클릭

        </script>
        <div class="cont on" id="tab_01">
          <div class="brd_navigator_bar2012_wrap">
            <div class="brd_navigator_bar2012">
              <ul class="ui_tab2_202012">
           
                </li>
                <li class="js-brand-tab-btn-2012" style="width:inherit">
                  <a
                    href="#"
                    id="#brd_cate_2"
                    onclick="GA_Event('공통_브랜드', '브랜드 탭', '남성');">
                    브랜드 별로 보기</a
                  >
                </li>
              </ul>
            </div>
          </div>

          <div class="brd_shop_cate_wrap2002">
            <input type="hidden" value="ko" id="brandCountryCd" />
            <input type="hidden" value="" id="whereLikeCancel" />

            <ul
              class="shop_list_brand one_depth"
              id="brd_cate_0"
              style="display: none">
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'TIME');">
                    <span class="logo">TIME</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR01_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR01', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br01/br01"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br01');GA_Event('공통_브랜드', '2DEPTH', 'TIME_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br01"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br01"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'TIME HOMME');">
                    <span class="logo">TIME HOMME</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ME_BR06_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ME_BR06', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br06/br06"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME HOMME_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br06');GA_Event('공통_브랜드', '2DEPTH', 'TIME HOMME_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/me09/br06"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME HOMME_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME HOMME_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me04/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '수트');">
                                수트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME HOMME_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME HOMME_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br06"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'SYSTEM');">
                    <span class="logo">SYSTEM</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR03_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR03', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br03/br03"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br03');GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br03"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br03"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'SYSTEM HOMME');">
                    <span class="logo">SYSTEM HOMME</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ME_BR07_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ME_BR07', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br07/br07"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM HOMME_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br07');GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM HOMME_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/me09/br07"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM HOMME_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM HOMME_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me04/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '수트');">
                                수트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM HOMME_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br07"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'MINE');">
                    <span class="logo">MINE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR02_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR02', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br02/br02"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br02');GA_Event('공통_브랜드', '2DEPTH', 'MINE_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br02"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_뷰티');">
                            뷰티</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/be02/br02"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '메이크업');">
                                메이크업</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'the CASHMERE');">
                    <span class="logo">the CASHMERE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR08_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR08', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br08/br08"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br08');GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_룩북');"
                            >룩북*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_뷰티');">
                            뷰티</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/be01/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스킨케어');">
                                스킨케어</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/be02/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '메이크업');">
                                메이크업</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/be03/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '바디/헤어케어');">
                                바디/헤어케어</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/be04/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '향수');">
                                향수</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls02/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '배스');">
                                배스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls03/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키친');">
                                키친</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls04/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '데스크');">
                                데스크</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls06/br08"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키즈');">
                                키즈</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'LANVIN COLLECTION');">
                    <span class="logo">LANVIN COLLECTION</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR19_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR19', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br19/br19"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br19');GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_룩북');"
                            >룩북*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br19"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'LANVIN BLANC');">
                    <span class="logo lanvin-blanc"
                      >LANVIN BLANC
                    </span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR63_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR63', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br63/br63"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br63');GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_룩북');"
                            >룩북*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="/ko/c/gf01/br63"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_여성웨어');">
                            여성웨어</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/gf02/br63"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_남성웨어');">
                            남성웨어</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/gf03/br63"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_ACC');">
                            ACC</a
                          >
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'CLUB MONACO');">
                    <span class="logo">CLUB MONACO</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR44_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR44', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br44/br44"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br44');GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/me09/br44"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_SPECIAL SHOP*');">
                            SPECIAL SHOP*(M)
                          </a>
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me04/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '수트');">
                                수트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br44"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'SJSJ');">
                    <span class="logo">SJSJ</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR04_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR04', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br04/br04"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br04');GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br04"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br04"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'LÄTT');">
                    <span class="logo">LÄTT</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR31_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR31', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br31/br31"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br31');GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br31"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br31"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'OBZEE');">
                    <span class="logo">OBZEE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR43_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR43', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br43/br43"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br43');GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br43"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br43"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'O&amp;#039;2nd');">
                    <span class="logo">O'2nd</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR45_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR45', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br45/br45"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br45');GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_룩북');"
                            >룩북*</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br45"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br45"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'TOM GREYHOUND');">
                    <span class="logo">TOM GREYHOUND</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR15_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR15', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br15/br15"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_브랜드');"
                            tab="BR15"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br15"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_SPECIAL SHOP*');">
                            SPECIAL SHOP*(W)
                          </a>
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_뷰티');">
                            뷰티</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/be03/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '바디/헤어케어');">
                                바디/헤어케어</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'TOM GREYHOUND_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls02/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '배스');">
                                배스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls03/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키친');">
                                키친</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls04/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '데스크');">
                                데스크</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls05/br15"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '펫');">
                                펫</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'FOURM THE STORE');">
                    <span class="logo">FOURM THE STORE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR35_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR35', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br35/br35"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_브랜드');"
                            tab="BR35"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/we09/br35"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_SPECIAL SHOP*');">
                            SPECIAL SHOP*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM THE STORE_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls02/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '배스');">
                                배스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls03/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키친');">
                                키친</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls06/br35"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키즈');">
                                키즈</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'FOURM STUDIO');">
                    <span class="logo">FOURM STUDIO</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR30_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR30', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br30/br30"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM STUDIO_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM STUDIO_브랜드');"
                            tab="BR30"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM STUDIO_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM STUDIO_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM STUDIO_뷰티');">
                            뷰티</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/be03/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '바디/헤어케어');">
                                바디/헤어케어</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/be04/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '향수');">
                                향수</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM STUDIO_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls02/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '배스');">
                                배스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls03/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키친');">
                                키친</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls04/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '데스크');">
                                데스크</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls06/br30"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키즈');">
                                키즈</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'FOURM MEN&amp;#039;S LOUNGE');">
                    <span class="logo">FOURM MEN'S LOUNGE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR32_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR32', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br32/br32"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM MEN&amp;#039;S LOUNGE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM MEN&amp;#039;S LOUNGE_브랜드');"
                            tab="BR32"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM MEN&amp;#039;S LOUNGE_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM MEN&amp;#039;S LOUNGE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'FOURM MEN&amp;#039;S LOUNGE_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls03/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키친');">
                                키친</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls04/br32"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '데스크');">
                                데스크</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'MUE');">
                    <span class="logo">MUE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR16_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR16', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br16/br16"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MUE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MUE_브랜드');"
                            tab="BR16"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MUE_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MUE_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MUE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'MUE_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls04/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '데스크');">
                                데스크</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls06/br16"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '키즈');">
                                키즈</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'H : SCENE');">
                    <span class="logo">H : SCENE</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR47_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR47', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br47/br47"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'H : SCENE_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'H : SCENE_브랜드');"
                            tab="BR47"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'H : SCENE_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br47"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'H : SCENE_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br47"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br47"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br47"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br47"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br47"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'BALLY');">
                    <span class="logo">BALLY</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_OS_BR21_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_OS_BR21', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br21/br21"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'BALLY_전체보기');"
                            >전체보기</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'BALLY_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'BALLY_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br21"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'LANVIN PARIS');">
                    <span class="logo">LANVIN PARIS</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_OS_BR20_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_OS_BR20', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br20/br20"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN PARIS_전체보기');"
                            >전체보기</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN PARIS_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN PARIS_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br20"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', '3.1 Phillip Lim');">
                    <span class="logo">3.1 Phillip Lim</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_OS_BR41_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_OS_BR41', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br41/br41"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', '3.1 Phillip Lim_전체보기');"
                            >전체보기</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', '3.1 Phillip Lim_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', '3.1 Phillip Lim_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br41"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'ROCHAS');">
                    <span class="logo">ROCHAS</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_OS_BR37_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_OS_BR37', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br37/br37"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'ROCHAS_전체보기');"
                            >전체보기</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'ROCHAS_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'ROCHAS_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br37"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'oera');">
                    <span class="logo oera">oera</span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_WE_BR61_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR61', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br61/br61"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_전체보기');"
                            >전체보기</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="/ko/c/be01/br61"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_스킨케어');">
                            스킨케어</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/be02/br61"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_메이크업');">
                            메이크업</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="/ko/c/be03/br61"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_바디/헤어케어');">
                            바디/헤어케어</a
                          >
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'Liquides Perfume Bar');">
                    <span class="logo liquide"
                      >Liquides Perfume Bar</span
                    >
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_ED_BR62_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ED_BR62', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br62/br62"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'Liquides Perfume Bar_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <!-- 													</a> -->
                          <a
                            href=".hs_brand_category_pop2"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'Liquides Perfume Bar_브랜드');"
                            tab="BR62"
                            id="categoryPopup"
                            class="link btn_popup_trigger"
                            >브랜드</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'Liquides Perfume Bar_뷰티');">
                            뷰티</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/be04/br62"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '향수');">
                                향수</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'Liquides Perfume Bar_라이프스타일');">
                            라이프스타일</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/ls01/br62"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                홈</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="javascript:noLink();"
                    class="one_link"
                    onclick="GA_Event('공통_브랜드', '1DEPTH', 'OUR LEGACY');">
                    <span class="logo ourlegacy"
                      >OUR LEGACY<img
                        class=""
                        alt="newIcon" />
                    </span>
                  </a>
                  <button
                    type="button"
                    id="gnb_br_NEW_NORMAL_BRANDS_OS_BR64_like_0"
                    class="like"
                    onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_OS_BR64', 'all');">
                    찜하기</button
                  ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                </div>
                <div class="two_depth" style="">
                  <div class="two-depth-arrow">
                    <div class="two_depth_inner">
                      <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                          <a
                            href="/ko/c/br64/br64"
                            class="two_link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OUR LEGACY_전체보기');"
                            >전체보기</a
                          >
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:void(0);"
                            class="two_link"
                            onclick="moveBrandLookBook('br64');GA_Event('공통_브랜드', '2DEPTH', 'OUR LEGACY_룩북');"
                            >룩북*</a
                          >
                        </li>
                      </ul>
                      <ul class="depth_two depth_two_02">
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OUR LEGACY_여성');">
                            여성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/we05/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we10/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we01/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we04/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                드레스</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we02/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/we03/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                스커트</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OUR LEGACY_남성');">
                            남성</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/me03/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                아우터</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me10/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                니트</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me01/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                탑</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/me02/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                팬츠</a
                              >
                            </li>
                          </ul>
                        </li>
                        <li class="two_lists">
                          <a
                            href="javascript:noLink();"
                            class="two_link js-brand-link"
                            onclick="GA_Event('공통_브랜드', '2DEPTH', 'OUR LEGACY_잡화');">
                            잡화</a
                          >
                          <ul class="three_depth" style="">
                            <li class="three_lists">
                              <a
                                href="/ko/c/as01/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                여성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as02/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                남성슈즈</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as03/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                여성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as04/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                남성백</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as05/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                머플러/스카프</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as06/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                주얼리</a
                              >
                            </li>
                            <li class="three_lists">
                              <a
                                href="/ko/c/as07/br64"
                                class="three_link"
                                onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                기타 ACC</a
                              >
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
            </ul>

            <div class="brd_shop_cate_wrap2002_single">
              <ul
                class="shop_list_brand one_depth"
                id="brd_cate_1"
                style="display: none">
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'TIME');">
                      <span class="logo">TIME</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR01"
                      value="NEW_NORMAL_BRANDS_WE#BR01#TIME#0#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR01_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR01', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br01/br01"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br01');GA_Event('공통_브랜드', '2DEPTH', 'TIME_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br01"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'TIME_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br01"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'MINE');">
                      <span class="logo">MINE</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR02"
                      value="NEW_NORMAL_BRANDS_WE#BR02#MINE#1#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR02_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR02', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br02/br02"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br02');GA_Event('공통_브랜드', '2DEPTH', 'MINE_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br02"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'MINE_뷰티');">
                              뷰티</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/be02/br02"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '메이크업');">
                                  메이크업</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'LANVIN COLLECTION');">
                      <span class="logo">LANVIN COLLECTION</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR19"
                      value="NEW_NORMAL_BRANDS_WE#BR19#LANVIN COLLECTION#2#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR19_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR19', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br19/br19"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br19');GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_룩북');"
                              >룩북*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN COLLECTION_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br19"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'LANVIN BLANC');">
                      <span class="logo lanvin-blanc"
                        >LANVIN BLANC<img
                          class=""
                          alt="newIcon" />
                      </span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR63"
                      value="NEW_NORMAL_BRANDS_WE#BR63#LANVIN BLANC#3#false#2" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR63_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR63', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br63/br63"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br63');GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_룩북');"
                              >룩북*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="/ko/c/gf01/br63"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_여성웨어');">
                              여성웨어</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/gf02/br63"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_남성웨어');">
                              남성웨어</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/gf03/br63"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_ACC');">
                              ACC</a
                            >
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'SYSTEM');">
                      <span class="logo">SYSTEM</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR03"
                      value="NEW_NORMAL_BRANDS_WE#BR03#SYSTEM#4#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR03_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR03', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br03/br03"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br03');GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br03"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SYSTEM_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br03"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'SJSJ');">
                      <span class="logo">SJSJ</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR04"
                      value="NEW_NORMAL_BRANDS_WE#BR04#SJSJ#5#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR04_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR04', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br04/br04"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br04');GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br04"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'SJSJ_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br04"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'the CASHMERE');">
                      <span class="logo">the CASHMERE</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR08"
                      value="NEW_NORMAL_BRANDS_WE#BR08#the CASHMERE#6#false#2" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR08_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR08', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br08/br08"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br08');GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_룩북');"
                              >룩북*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_뷰티');">
                              뷰티</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/be01/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스킨케어');">
                                  스킨케어</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/be02/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '메이크업');">
                                  메이크업</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/be03/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '바디/헤어케어');">
                                  바디/헤어케어</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/be04/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '향수');">
                                  향수</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'the CASHMERE_라이프스타일');">
                              라이프스타일</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/ls01/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '홈');">
                                  홈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/ls02/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '배스');">
                                  배스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/ls03/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '키친');">
                                  키친</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/ls04/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '데스크');">
                                  데스크</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/ls06/br08"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '키즈');">
                                  키즈</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'LÄTT');">
                      <span class="logo">LÄTT</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR31"
                      value="NEW_NORMAL_BRANDS_WE#BR31#LÄTT#7#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR31_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR31', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br31/br31"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br31');GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br31"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LÄTT_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br31"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'OBZEE');">
                      <span class="logo">OBZEE</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR43"
                      value="NEW_NORMAL_BRANDS_WE#BR43#OBZEE#8#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR43_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR43', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br43/br43"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br43');GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br43"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'OBZEE_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br43"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'O&amp;#039;2nd');">
                      <span class="logo">O'2nd</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR45"
                      value="NEW_NORMAL_BRANDS_WE#BR45#O'2nd#9#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR45_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR45', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br45/br45"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br45');GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_룩북');"
                              >룩북*</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/we09/br45"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_SPECIAL SHOP*');">
                              SPECIAL SHOP*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'O&amp;#039;2nd_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as01/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성슈즈');">
                                  여성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br45"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'CLUB MONACO');">
                      <span class="logo">CLUB MONACO</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR44"
                      value="NEW_NORMAL_BRANDS_WE#BR44#CLUB MONACO#10#false#2" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR44_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR44', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br44/br44"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:void(0);"
                              class="two_link"
                              onclick="moveBrandLookBook('br44');GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_룩북');"
                              >룩북*</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_여성');">
                              여성</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we05/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
                                  아우터</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we10/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '니트');">
                                  니트</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we01/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '탑');">
                                  탑</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we04/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '드레스');">
                                  드레스</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we02/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '팬츠');">
                                  팬츠</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/we03/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '스커트');">
                                  스커트</a
                                >
                              </li>
                            </ul>
                          </li>
                          <li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'CLUB MONACO_잡화');">
                              잡화</a
                            >
                            <ul class="three_depth" style="">
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as02/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '남성슈즈');">
                                  남성슈즈</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as03/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '여성백');">
                                  여성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as04/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '남성백');">
                                  남성백</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as05/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '머플러/스카프');">
                                  머플러/스카프</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as06/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '주얼리');">
                                  주얼리</a
                                >
                              </li>
                              <li class="three_lists">
                                <a
                                  href="/ko/c/as07/br44"
                                  class="three_link"
                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '기타 ACC');">
                                  기타 ACC</a
                                >
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="one_lists">
                  <div class="one_wrap">
                    <a
                      href="javascript:noLink();"
                      class="one_link"
                      onclick="GA_Event('공통_브랜드', '1DEPTH', 'oera');">
                      <span class="logo oera">oera</span>
                    </a>
                    <input
                      type="hidden"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR61"
                      value="NEW_NORMAL_BRANDS_WE#BR61#oera#11#false#1" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_WE_BR61_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_WE_BR61', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
                  <div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                          <li class="two_lists">
                            <a
                              href="/ko/c/br61/br61"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_전체보기');"
                              >전체보기</a
                            >
                          </li>
                        </ul>
                        <ul class="depth_two depth_two_02">
                          <li class="two_lists">
                            <a
                              href="/ko/c/be01/br61"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_스킨케어');">
                              스킨케어</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/be02/br61"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_메이크업');">
                              메이크업</a
                            >
                          </li>
                          <li class="two_lists">
                            <a
                              href="/ko/c/be03/br61"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'oera_바디/헤어케어');">
                              바디/헤어케어</a
                            >
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>
              </ul>
              <ul
                class="shop_list_brand one_depth"
                id="brd_cate_2"
                style="display: block">
                 </ul>

              <div
                class="flag_fixed_wrap2002"
                style="visibility: hidden">
                <div
                  id="fadeEff"
                  class="flag_wrap scroll_flag swiper-container swiper-container-horizontal swiper-container-free-mode swiper-container-android swiper-container-wp8-horizontal">
                  <ul
                    class="swiper-wrapper"
                    id="setBrandList"
                    style="transform: translate3d(0px, 0px, 0px)">
                    <!-- 찜한 브랜드가 한 개라도 있으면 ul 태그에 active 클래스 붙임 -->
                  </ul>
                  <div class="my_heart_flag2002">
                    <div>
                      MY<span class="ico_heart200214">heart</span>
                      <span class="white_action200219"></span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--// 브랜드 -->
        <!-- 카테고리 -->
        <script type="text/javascript">
          var categoryCode = '';
          var categoryName = '';
          var flag = '';
          var processYn = false;

          var lnbLinkUrl = '';
          var lnbImageUrl = '';
          var lnbMenuName = '';
          $(document).ready(function () {
            shopCateListHeightSet(); //카테고리 텝 컨텐츠 높이 설정
            setLnbMenu(); //이미지메뉴 셋팅
          });
          function setLnbMenu() {
            var html = '';
            var liveCommerceBannerHtml = '';

            html += '<li>';
            html +=
              "<a href=\"/magazine/lookbook\" onclick=\"GA_Event('공통_카테고리', '컨텐츠', '룩북');\">";
            html += '<div class="maga_wrap">';
            html += '<div class="circle_img">';
            html +=
              '<img src="http://cdn.thehandsome.com/mobile/lnb/image/20220421_37113375502350198_ko.jpg" alt="circle img">';
            html += '</div>';
            html += '<p class="maga_txt">룩북</p>';
            html += '</div>';
            html += '</a>';
            html += '</li>';

            html += '<li>';
            html +=
              "<a href=\"/magazine/exhibitions\" onclick=\"GA_Event('공통_카테고리', '컨텐츠', '기획전');\">";
            html += '<div class="maga_wrap">';
            html += '<div class="circle_img">';
            html +=
              '<img src="http://cdn.thehandsome.com/mobile/lnb/image/20220421_37113357562565650_ko.jpg" alt="circle img">';
            html += '</div>';
            html += '<p class="maga_txt">기획전</p>';
            html += '</div>';
            html += '</a>';
            html += '</li>';

            html += '<li>';
            html +=
              "<a href=\"/magazine/events\" onclick=\"GA_Event('공통_카테고리', '컨텐츠', '이벤트');\">";
            html += '<div class="maga_wrap">';
            html += '<div class="circle_img">';
            html +=
              '<img src="http://cdn.thehandsome.com/mobile/lnb/image/20220421_37113881020441632_ko.jpg" alt="circle img">';
            html += '</div>';
            html += '<p class="maga_txt">이벤트</p>';
            html += '</div>';
            html += '</a>';
            html += '</li>';

            html += '<li>';
            html +=
              "<a href=\"/magazine/submain\" onclick=\"GA_Event('공통_카테고리', '컨텐츠', 'THE 매거진');\">";
            html += '<div class="maga_wrap">';
            html += '<div class="circle_img">';
            html +=
              '<img src="http://cdn.thehandsome.com/mobile/lnb/image/20220421_37113368536417995_ko.jpg" alt="circle img">';
            html += '</div>';
            html += '<p class="maga_txt">THE 매거진</p>';
            html += '</div>';
            html += '</a>';
            html += '</li>';

            $(
              '.lnb_themaga_sns_wrap2004 .lnb_themagazine_2004 ul'
            ).append(html);
            if ('방송 전/후' != '') {
              liveCommerceBannerHtml +=
                '<div class="live_shopping_banner2008">';
              liveCommerceBannerHtml +=
                "<a href=\"/live/liveTV\" onclick=\"GA_Event('공통_카테고리', '컨텐츠', '핸썸TV');\">";
              liveCommerceBannerHtml +=
                '<img src="http://cdn.thehandsome.com/_ui/handsomemobile/images/event/banner_handsometv_vod.png" alt="circle img">';
              liveCommerceBannerHtml += '</a>';
              liveCommerceBannerHtml += '</div>';

              $(
                '.lnb_themaga_sns_wrap2004 .lnb_themagazine_2004'
              ).after(liveCommerceBannerHtml);
            }
          }
          function applyCategory(id) {
            if (!processYn) {
              var valueSplitArray = $('#' + id)
                .val()
                .split('|');

              $('#' + id + '_like').removeClass('on');
              var lc = new layerConfirm(
                '로그인 후 당신만의 선호 카테고리를 <br /> 선택하세요.',
                '',
                ''
              );
              lc.cancleAction = function () {
                //취소 호출 펑션
                $('#' + id + '_like').removeClass('on');
                if ($('#setUserCategory li').length == 0) {
                  $(quickSCaterogyTagSelector).removeClass('active');
                }
                return;
              };

              lc.confirmAction = function () {
                //확인 호출 펑션
                location.href = '/ko/member/login';
              };

              $('#confirmCloseDiv').click(function () {
                lc.cancleAction();
              });

              $('#' + id + '_like').removeClass('on');
            }
          }

          function replaceSplitStr(str1, str2) {
            var replaceStr = '';
            var replaceStrArr = str1.split('|');
            var replaceStrCheck = 'N';
            for (var i = 0; i < replaceStrArr.length; i++) {
              if (replaceStrArr[i] == str2 && replaceStrCheck == 'N') {
                replaceStrCheck = 'Y';
              } else {
                if (replaceStr == '') {
                  replaceStr = replaceStrArr[i];
                } else {
                  replaceStr += '|' + replaceStrArr[i];
                }
              }
            }
            return replaceStr;
          }

          // 재정렬
          function replaceSplitStr2(str, idx) {
            var replaceStr = '';
            var replaceStrArr = str.split('|');
            for (var i = 0; i < replaceStrArr.length; i++) {
              if (i != idx) {
                if (replaceStr == '') {
                  replaceStr = replaceStrArr[i];
                } else {
                  replaceStr += '|' + replaceStrArr[i];
                }
              }
            }
            return replaceStr;
          }

          function replaceSplitIdx(str1, str2) {
            var replaceIdx = '';
            var replaceStrArr = str1.split('|');
            for (var i = 0; i < replaceStrArr.length; i++) {
              if (replaceStrArr[i] == str2) {
                replaceIdx = i;
                break;
              }
            }
            return replaceIdx;
          }

          function removeCategory(id) {
            if (!processYn) {
              processYn = true;
              var valueSplitArray = $('#' + id)
                .val()
                .split('|');
              var replaceIdx = '';
              replaceIdx = replaceSplitIdx(
                categoryCode,
                valueSplitArray[0]
              );
              categoryCode = replaceSplitStr(
                categoryCode,
                valueSplitArray[0]
              );
              //             categoryName = replaceSplitStr(categoryName,valueSplitArray[1]);
              //             flag = replaceSplitStr(flag,valueSplitArray[2]);
              categoryName = replaceSplitStr2(categoryName, replaceIdx);
              flag = replaceSplitStr2(flag, replaceIdx);
              $.ajax({
                type: 'GET',
                url: '/ko/intro/setUserCategory',
                data: { categoryName: '', categoryCode: '', flag: '' },
                success: function (data) {
                  $('#' + id).val(
                    valueSplitArray[0] +
                      '|' +
                      valueSplitArray[1] +
                      '|' +
                      valueSplitArray[2] +
                      '|false'
                  );
                  var setUserCategoryHtml = '';
                  var categoryCodeArr = categoryCode.split('|');
                  var categoryNameArr = categoryName.split('|');
                  var flagArr = flag.split('|');
                  for (
                    var i = categoryCodeArr.length - 1;
                    i >= 0;
                    i--
                  ) {
                    if (categoryCodeArr[i] != '') {
                      setUserCategoryHtml +=
                        '<li class="flag swiper-slide ' +
                        flagArr[i] +
                        ' active" style="margin-right: 6px;"> \n';
                      setUserCategoryHtml +=
                        '    <a href="/ko/c/' +
                        categoryCodeArr[i] +
                        '" class="name">' +
                        categoryNameArr[i] +
                        '</a> \n';
                      setUserCategoryHtml +=
                        '    <a href="javascript:noLink();" class="delete" onclick="removeCategory(\'gnb_' +
                        categoryCodeArr[i] +
                        '\');">삭제</a> \n';
                      setUserCategoryHtml += '</li> \n';
                    }
                  }

                  $('#setUserCategory').html(setUserCategoryHtml);
                  $('#' + id + '_like').removeClass('on');
                  if ($('#setUserCategory li').length == 0) {
                    $(quickSCaterogyTagSelector).removeClass('active');
                    $('.lnb_themaga_sns_wrap2004').css(
                      'padding-bottom',
                      '0'
                    );
                    var $shopCateList = $(
                      '.hand_footer_gnb_wrap2002 .cate_shop_cate_wrap2004 .shop_list_category'
                    );
                    var shopCateListH =
                      $(window).height() -
                      (107 +
                        $('.lnb_themaga_sns_wrap2004').outerHeight());
                    $shopCateList.css(
                      'min-height',
                      shopCateListH + 'px'
                    );
                  }

                  processYn = false;
                  if (categoryCode != '') {
                    // 남은 선호카테고리가 있을 때 재등록
                    setCategory(id, 'remove');
                  }
                },
                error: function (e) {
                  console.log('error', e);
                },
              });
            }
          }

          function setCategory(id, gubun) {
            if (!processYn) {
              processYn = true;
              var valueSplitArray = $('#' + id)
                .val()
                .split('|');
              $.ajax({
                type: 'GET',
                url: '/ko/intro/setUserCategory',
                data: {
                  categoryName: categoryName,
                  categoryCode: categoryCode,
                  flag: flag,
                },
                success: function (data) {
                  if (gubun == 'set') {
                    $('#' + id).val(
                      valueSplitArray[0] +
                        '|' +
                        valueSplitArray[1] +
                        '|' +
                        valueSplitArray[2] +
                        '|true'
                    );
                  }
                  var setUserCategoryHtml = '';
                  var categoryCodeArr = categoryCode.split('|');
                  var categoryNameArr = categoryName.split('|');
                  var flagArr = flag.split('|');
                  for (
                    var i = categoryCodeArr.length - 1;
                    i >= 0;
                    i--
                  ) {
                    setUserCategoryHtml +=
                      '<li class="flag swiper-slide ' +
                      flagArr[i] +
                      ' active" style="margin-right: 6px;"> \n';
                    setUserCategoryHtml +=
                      '	<a href="/ko/c/' +
                      categoryCodeArr[i] +
                      "\" class=\"name\" onclick=\"GA_Event('공통_카테고리', '좋아요한_카테고리', '" +
                      escape(categoryNameArr[i].replace('&44;', ',')) +
                      '\');" >' +
                      categoryNameArr[i] +
                      '</a> \n';
                    setUserCategoryHtml +=
                      '	<a href="javascript:noLink();" class="delete" onclick="removeCategory(\'gnb_' +
                      categoryCodeArr[i] +
                      '\');">삭제</a> \n';
                    setUserCategoryHtml += '</li> \n';
                  }

                  $('#setUserCategory').html(setUserCategoryHtml);
                  quickSCaterogyTagSwiper.update();
                  processYn = false;
                },
                error: function (e) {
                  console.log('error', e);
                },
              });
            }
          }

          function shopCateListHeightSet() {
            var winH = $(window).height();
            /* bottomConH = $('.lnb_themaga_sns_wrap2004').outerHeight(); */
            if ($('#setUserCategory').find('.flag').length >= 1) {
              // 즐겨찾기한 카테고리가 있을 때
              $('.lnb_themaga_sns_wrap2004').css(
                'padding-bottom',
                '56px'
              );
              var $shopCateList = $(
                '.hand_footer_gnb_wrap2002 .cate_shop_cate_wrap2004 .shop_list_category'
              );
              var shopCateListH =
                $(window).height() -
                (107 + $('.lnb_themaga_sns_wrap2004').outerHeight());
              $shopCateList.css('min-height', shopCateListH + 'px');
            } else {
              $('.lnb_themaga_sns_wrap2004').css('padding-bottom', '0');
              var $shopCateList = $(
                '.hand_footer_gnb_wrap2002 .cate_shop_cate_wrap2004 .shop_list_category'
              );
              var shopCateListH =
                $(window).height() -
                (107 + $('.lnb_themaga_sns_wrap2004').outerHeight());
              $shopCateList.css('min-height', shopCateListH + 'px');
            }
            var $shopCateList = $(
              '.hand_footer_gnb_wrap2002 .cate_shop_cate_wrap2004 .shop_list_category'
            );
            $shopCateList.css('min-height', shopCateListH + 'px');
          }
        </script>
        <div class="cont" id="tab_02">
 
        </div>
        <!--// 카테고리 -->
      </div>
    </div>
  </div>
</div>
<div class="footer">
  <a href="javascript:noLink();" class="close" onclick="closeMenu()"
    >닫기</a
  >
</div>
</div>

`;


$('.hsome_quickMenu .shop a').click(function (e) {
  e.preventDefault();
  console.log('shop click');
  
  	$.ajax({
		type: 'get',
		url: '/wck/products/category',
		success: function (result) {
						
			let categorylist = '';
			categorylist += `<div class="cate_shop_cate_wrap2004">
							<ul class="shop_list_category ctgr one_depth" style="min-height: 633px">`
            
              
            for(var i = 0; i<result.length; i++) {  
            categorylist += `
            
            <li class="one_lists">
                <div class="one_wrap">
                  <a
                    href="#"
                    class="one_link"
                    id="cate_we"
                    onclick="GA_Event('공통_카테고리', '1DEPTH', '여성');">
                    <span class="category_tit">${result[i].firstname}</span>
                  </a>
                </div>
                <ul class="two_depth" style="display: none">
                  <li class="two_lists">
                    <a
                      href="/wck/products/list?gender=${result[i].depth1name}"
                      class="two_link js-cate-link"
                      onclick="GA_Event('공통_카테고리', '2DEPTH', '여성_전체보기');"
                      >전체보기</a>
                  </li>`
                  
                  
               		for(var s = 0; s<result[i].secondCategory.length; s++)  {
						   categorylist +=
						   `<li class="two_lists">
						   <a
                      		href="#"
		                      class="two_link js-cate-link"
		                      onclick="GA_Event('공통_카테고리', '2DEPTH', '여성_아우터');"
		                      >${result[i].secondCategory[s].secondname}</a> 
		                      
		                      <ul class="three_depth" style="">
		                      <input
                        		type="hidden"
		                        id="gnb_we05"
		                        value="we05|아우터>전체|woman|false" />
				                <li class="three_lists">  
				                <button
			                     type="button"
			                     class="like"
			                     id="gnb_we05_like"
			                     onclick="applyCategory('gnb_we05');GA_Event('공통_카테고리', '좋아요', '여성_아우터_전체');">
			                     찜하기</button>
		                         <a
		                         href="/wck/products/list?gender=${result[i].depth1name}&secCat=${result[i].secondCategory[s].depth2name}"
	                             class="three_link"
		                         onclick="GA_Event('공통_카테고리', '3DEPTH', '여성_아우터_전체');"
		                         >전체</a></li>
							     
		                      
							`  
							
							
							for(var t = 0; t<result[i].secondCategory[s].thirdCategory.length; t++) {
								categorylist += `
								
								<input type="hidden" id="gnb_we051" value="we051|아우터>재킷|woman|false" />
	                      		<li class="three_lists">
	                        	<button
		                          type="button"
		                          class="like"
		                          id="gnb_we051_like"
		                          onclick="applyCategory('gnb_we051');GA_Event('공통_카테고리', '좋아요', '여성_아우터_재킷');">
		                          찜하기
		                        </button>
		                        <a
		                          href="/wck/products/list?gender=${result[i].depth1name}&thrCat=${result[i].secondCategory[s].thirdCategory[t].depth3name}"
		                          class="three_link"
		                          onclick="GA_Event('공통_카테고리', '3DEPTH', '여성_아우터_재킷');"
		                          >${result[i].secondCategory[s].thirdCategory[t].detailname}</a>
		                      	</li>		
								`
							}
				categorylist +=	`</ul></li>`
					}
              categorylist+=  ` </ul></li>`

             }                 
			 categorylist += `</ul></div>`
             
             
	$("#tab_02").html(categorylist);
		}, 
		error : function (result) {
			console.log("통신 실패");
		}
	})
	
	
	$.ajax({
			type: 'get',
			url: '/wck/products/brand',
			success: function (result) {
				
				console.log(result);
				
				brandName = '';
				for(var i = 0; i<result.length; i++) {
					
					brandName += `<li class="one_lists">
					 <div class="one_wrap">
					 <a href="javascript:noLink();"
					 class="one_link"
					 onclick="GA_Event('공통_브랜드', '1DEPTH', 'LANVIN BLANC');">
					 <span class="logo lanvin-blanc"
					 >${result[i].brandname}
                     </span>
                     </a>
                     <input
                     type="hidden"
                     id="gnb_br_NEW_NORMAL_BRANDS_ME_BR63"
                      value="NEW_NORMAL_BRANDS_ME#BR63#LANVIN BLANC#0#false#2" />
                    <button
                      type="button"
                      id="gnb_br_NEW_NORMAL_BRANDS_ME_BR63_like"
                      class="like"
                      onclick="applyNavBrand('gnb_br_NEW_NORMAL_BRANDS_ME_BR63', 'single');">
                      찜하기</button
                    ><!-- 찜한 브랜드에 on 클래스 붙음 -->
                  </div>
					`
					brandName += `<div class="two_depth" style="">
                    <div class="two-depth-arrow">
                      <div class="two_depth_inner">
                        <ul class="depth_two depth_two_01">
                        <li class="two_lists">
                            <a
                              href="/wck/products/brandinfo?brand=${result[i].brandcode}"
                              class="two_link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_전체보기');"
                              >전체보기</a
                            >
                          </li>
                          </ul>
                          <ul class="depth_two depth_two_02">`
					
					for(var s = 0; s<result[i].firstCategory.length; s++){
						brandName += `
						<li class="two_lists">
                            <a
                              href="javascript:noLink();"
                              class="two_link js-brand-link"
                              onclick="GA_Event('공통_브랜드', '2DEPTH', 'LANVIN BLANC_여성웨어');">
                              ${result[i].firstCategory[s].firstname}</a>
                              <ul class="three_depth" style="">
                          `
                          
                          	for(var t = 0; t<result[i].firstCategory[s].secondCategory.length; t++){
								brandName += `
								<li class="three_lists">
		                                <a
		                                  href="/wck/products/brandinfo?brand=${result[i].brandcode}&gender=${result[i].firstCategory[s].depth1name}&secCat=${result[i].firstCategory[s].secondCategory[t].depth2name}"
		                                  class="three_link"
		                                  onclick="GA_Event('공통_브랜드', '3DEPTH', '아우터');">
		                                  ${result[i].firstCategory[s].secondCategory[t].secondname}</a
		                                >
		                              </li>
								`
							}
						brandName += '</ul></li> '
					}
					brandName += `</ul> `
					brandName += `</div> </div> </div> </li>`
			
				}
				
				
				
				
				
				    
               $("#brd_cate_2").html(brandName);
			
			}, 
			error : function () {
				console.log("통신 실패");
			}
		})
			
			

	
	
  $('.hsome_quickMenu_contents').addClass('active absolute');
  $('.hsome_quickMenu_contents').html(template);
});

$('.hsome_quickMenu  .sns a').click(function(e){
	e.preventDefault();
	console.log('sns click');
})
$('.hsome_quickMenu .home a').click(function(e){
	e.preventDefault();
	console.log('home click');
})
$('.hsome_quickMenu .myheart a').click(function(e){
	e.preventDefault();
	console.log('my heart click');
})
$('.hsome_quickMenu .mypage a').click(function(e){
	e.prevnetDefault();
	console.log('mypage click')
})


function closeMenu() {
  console.log('tt');
  $('body').removeClass('hold_body');
  $('.hsome_quickMenu_contents').removeClass('active absolute');
  $('.hsome_quickMenu_contents').html('');
}
