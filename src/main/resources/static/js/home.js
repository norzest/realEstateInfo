    $(function(){
        setDateBox();
    });

    //////////////////// 남는 길이만큼 0으로 채움 ////////////////////
    function fillZero(width, str) {
        return str.length >= width ? str:new Array(width-str.length+1).join('0')+str;
    }


    //////////////////// Api 불러오기 ////////////////////
    function getApi(y, m, doSi, siGunGu) {
        // 지역 코드화
        function getStateCode(ds, sgg) {
            var dsCode = {
                "서울특별시" : "11",
                "경기도" : "41",
                "인천광역시" : "28",
                "강원도" : "42",
                "충청북도" : "43",
                "충청남도" : "44",
                "대전광역시" : "30",
                "세종특별자치시" : "36",
                "전라북도" : "45",
                "전라남도" : "46",
                "광주광역시" : "29",
                "경상북도" : "47",
                "경상남도" : "48",
                "부산광역시" : "26",
                "대구광역시" : "27",
                "울산광역시" : "31",
                "제주특별자치도" : "50",
            };
            var sggCode = {
                "11" : { // 서울특별시
                    "강남구" : "680", "강동구" : "740", "강북구" : "305",
                    "강서구" : "500", "관악구" : "620", "광진구" : "215",
                    "구로구" : "530", "금천구" : "545", "노원구" : "350",
                    "도봉구" : "320", "동대문구" : "230", "동작구" : "590",
                    "마포구" : "440", "서대문구" : "410", "서초구" : "650",
                    "성동구" : "200", "성북구" : "290", "송파구" : "710",
                    "양천구" : "470", "영등포구" : "560", "용산구" : "170",
                    "은평구" : "380", "종로구" : "110", "중구" : "140", "중랑구" : "260"
                },
                "41" : { // 경기도
                    "가평군" : "820", "고양시" : "280", "고양시 덕양구" : "281",
                    "고양시 일산동구" : "285", "고양시 일산서구" : "287", "과천시" : "290",
                    "광명시" : "210", "광주시" : "610", "구리시" : "310",
                    "군포시" : "410", "김포시" : "570", "남양주시" : "360",
                    "동두천시" : "250", "부천시" : "190", "성남시" : "130",
                    "성남시 분당구" : "135", "성남시 수정구" : "131", "성남시 중원구" : "133",
                    "수원시" : "110", "수원시 권선구" : "113", "수원시 영통구" : "117",
                    "수원시 장안구" : "111", "수원시 팔달구" : "115", "시흥시" : "390",
                    "안산시" : "270", "안산시 단원구" : "273", "안산시 상록구" : "271",
                    "안성시" : "550", "안양시" : "170", "안양시 동안구" : "173",
                    "안양시 만안구" : "171", "양주시" : "630", "양평군" : "830",
                    "여주시" : "670", "연천군" : "800", "오산시" : "370",
                    "용인시" : "460", "용인시 기흥구" : "463", "용인시 수지구" : "465",
                    "용인시 처인구" : "461", "의왕시" : "430", "의정부시" : "150",
                    "이천시" : "500", "파주시" : "480", "평택시" : "220",
                    "포천시" : "650", "하남시" : "450", "화성시" : "590"
                },
                "28" : { // 인천광역시
                    "강화군" : "710", "계양구" : "245", "남동구" : "200",
                    "동구" : "140", "미추홀구" : "177", "부평구" : "237",
                    "서구" : "260", "연수구" : "185", "옹진군" : "720", "중구" : "110"
                },
                "42" : { // 강원도
                    "강릉시" : "150", "고성군" : "820", "동해시" : "170",
                    "삼척시" : "230", "속초시" : "210", "양구군" : "800",
                    "양양군" : "830", "영월군" : "750", "원주시" : "130",
                    "인제군" : "810", "정선군" : "770", "철원군" : "780",
                    "춘천시" : "110", "태백시" : "190", "평창군" : "760",
                    "홍천군" : "720", "화천군" : "790", "횡성군" : "730"
                },
                "43" : { // 충청북도
                    "괴산군" : "760", "단양군" : "800", "보은군" : "720",
                    "영동군" : "740", "옥천군" : "730", "음성군" : "770",
                    "제천시" : "150", "증평군" : "745", "진천군" : "750",
                    "청주시" : "110", "청주시 상당구" : "111", "청주시 서원구" : "112",
                    "청주시 청원구" : "114", "청주시 흥덕구" : "113", "충주시" : "130"
                },
                "44" : { // 충청남도
                    "계룡시" : "250", "공주시" : "150", "금산군" : "710",
                    "논산시" : "230", "당진시" : "270", "보령시" : "180",
                    "부여군" : "760", "서산시" : "210", "서천군" : "770",
                    "아산시" : "200", "예산군" : "810", "천안시" : "130",
                    "천안시 동남구" : "131", "천안시 서북구" : "133", "청양군" : "790",
                    "태안군" : "825", "홍성군" : "800"
                },
                "30" : { // 대전광역시
                    "대덕구" : "230", "동구" : "110", "서구" : "170",
                    "유성구" : "200", "중구" : "140"
                },
                "36" : { // 세종특별자치시
                    "세종특별자치시" : "110"
                },
                "45" : { // 전라북도
                    "고창군" : "790", "군산시" : "130", "김제시" : "210",
                    "남원시" : "190", "무주군" : "730", "부안군" : "800",
                    "순창군" : "770", "완주군" : "710", "익산시" : "140",
                    "임실군" : "750", "장수군" : "740", "전주시" : "110",
                    "전주시 덕진구" : "113", "전주시 완산구" : "111", "정읍시" : "180", "진안군" : "720"
                },
                "46" : { // 전라남도
                    "강진군" : "810", "고흥군" : "770", "곡성군" : "720",
                    "광양시" : "230", "구례군" : "730", "나주시" : "170",
                    "담양군" : "710", "목포시" : "110", "무안군" : "840",
                    "보성군" : "780", "순천시" : "150", "신안군" : "910",
                    "여수시" : "130", "영광군" : "870", "영암군" : "830",
                    "완도군" : "890", "장성군" : "880", "장흥군" : "800",
                    "진도군" : "900", "함평군" : "860", "해남군" : "820", "화순군" : "790"
                },
                "29" : { // 광주광역시
                    "광산구" : "200", "남구" : "155", "동구" : "110",
                    "북구" : "170", "서구" : "140"
                },
                "47" : { // 경상북도
                    "경산시" : "290", "경주시" : "130", "고령군" : "830",
                    "구미시" : "190", "군위군" : "720", "김천시" : "150",
                    "문경시" : "280", "봉화군" : "920", "상주시" : "250",
                    "성주군" : "840", "안동시" : "170", "영덕군" : "770",
                    "영양군" : "760", "영주시" : "210", "영천시" : "230",
                    "예천군" : "900", "울릉군" : "940", "울진군" : "930",
                    "의성군" : "730", "청도군" : "820", "청송군" : "750",
                    "칠곡군" : "850", "포항시" : "110", "포항시 남구" : "111", "포항시 북구" : "113"
                },
                "48" : { // 경상남도
                    "거제시" : "310", "거창군" : "880", "고성군" : "820",
                    "김해시" : "250", "남해군" : "840", "밀양시" : "270",
                    "사천시" : "240", "산청군" : "860", "양산시" : "330",
                    "의령군" : "720", "진주시" : "170", "창녕군" : "740",
                    "창원시" : "120", "창원시 마산합포구" : "125", "창원시 마산회원구" : "127",
                    "창원시 성산구" : "123", "창원시 의창구" : "121", "창원시 진해구" : "129",
                    "통영시" : "220", "하동군" : "850", "함안군" : "730",
                    "함양군" : "870", "합천군" : "890"
                },
                "26" : { // 부산광역시
                    "강서구" : "440", "금정구" : "410", "기장군" : "710",
                    "남구" : "290", "동구" : "170", "동래구" : "260",
                    "부산진구" : "230", "북구" : "320", "사상구" : "530",
                    "사하구" : "380", "서구" : "140", "수영구" : "500",
                    "연제구" : "470", "영도구" : "200", "중구" : "110", "해운대구" : "350"
                },
                "27" : { // 대구광역시
                    "남구" : "200", "달서구" : "290", "달성군" : "710",
                    "동구" : "140", "북구" : "230", "서구" : "170",
                    "수성구" : "260", "중구" : "110"
                },
                "31" : { // 울산광역시
                    "남구" : "140", "동구" : "170", "북구" : "200",
                    "울주군" : "710", "중구" : "110"
                },
                "50" : { // 제주특별자치도
                    "서귀포시" : "130", "제주시" : "110"
                }
            };

            return dsCode[ds] + sggCode[dsCode[ds]][sgg];
        }

        m = fillZero(2, m);
        var cd = getStateCode(doSi, siGunGu);
        var getYmd = "?ymd=" + y + m;
        var getCd = "&cd=" + cd;

        $("#apiTest").empty();
        $.ajax({
            url:'http://localhost:8081/getRSApi' + getYmd + getCd,
            type:'GET',
            dataType: 'xml',
            success: function(data) {
                $(data).find("item").each(function() {
                    var year = $(this).find("년").text();
                    var dong = $(this).find("법정동").text();
                    var apart = $(this).find("아파트").text();

                    var text = year + dong + " " + apart + "</br>";

                    $("#apiTest").append(text);
                });
            },
            error:function(request, error) {
                console.log("code: " + request.status + "\n" +
                            "message: " + request.responseText + "\n" +
                            "error: " + error);
            }
        });
    };

    //////////////////// select 박스 채우기 ////////////////////
    function setDateBox() {
        // select 박스 초기화
        function init(box) {
            $(box).empty().append("<option value=''>선택</option>");
        };

        // 월 추가
        function addMonth() {
            for(var i = 1; i <= 12; i++){
                $("#month_select").append("<option value='"+ i +"'>"+ i + " 월" +"</option>");
            }
        };

        init("#year_select");
        init("#month_select");
        init("#do_select");
        init("#si_select");

        var dt = new Date();
        var year = "";
        var com_year = dt.getFullYear();

        // 년도 추가 -30년부터 -1년
        for(var y = (com_year-30); y <= (com_year-1); y++){
            $("#year_select").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
        }
        addMonth();



        var area = {
            "서울특별시" : [ "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" ],
            "경기도" : [ "수원시 장안구", "수원시 권선구", "수원시 팔달구", "수원시 영통구", "성남시 수정구", "성남시 중원구", "성남시 분당구", "의정부시", "안양시 만안구", "안양시 동안구", "부천시", "광명시", "평택시", "동두천시", "안산시 상록구", "안산시 단원구", "고양시 덕양구", "고양시 일산동구",
                "고양시 일산서구", "과천시", "구리시", "남양주시", "오산시", "시흥시", "군포시", "의왕시", "하남시", "용인시 처인구", "용인시 기흥구", "용인시 수지구", "파주시", "이천시", "안성시", "김포시", "화성시", "광주시", "양주시", "포천시", "여주시", "연천군", "가평군", "양평군" ],
            "인천광역시" : [ "계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군" ],
            "강원도" : [ "춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군" ],
            "충청북도" : [ "청주시 상당구", "청주시 서원구", "청주시 흥덕구", "청주시 청원구", "충주시", "제천시", "보은군", "옥천군", "영동군", "증평군", "진천군", "괴산군", "음성군", "단양군" ],
            "충청남도" : [ "천안시 동남구", "천안시 서북구", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군" ],
            "대전광역시" : [ "대덕구", "동구", "서구", "유성구", "중구" ],
            "세종특별자치시" : [ "세종특별자치시" ],
            "전라북도" : [ "전주시 완산구", "전주시 덕진구", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군" ],
            "전라남도" : [ "목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군" ],
            "광주광역시" : [ "광산구", "남구", "동구", "북구", "서구" ],
            "경상북도" : [ "포항시 남구", "포항시 북구", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군" ],
            "경상남도" : [ "창원시 의창구", "창원시 성산구", "창원시 마산합포구", "창원시 마산회원구", "창원시 진해구", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군" ],
            "부산광역시" : [ "강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군" ],
            "대구광역시" : [ "남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군" ],
            "울산광역시" : [ "남구", "동구", "북구", "중구", "울주군" ],
            "제주특별자치도" : [ "서귀포시", "제주시" ]
        };

        // 도/특별시 select 박스 추가
        var areaKeys = Object.keys(area);
        areaKeys.forEach(function(region){
            $("#do_select").append("<option value="+region+">"+region+"</option>");
        });



        // select 박스 변경 이벤트
        //년도
        $(document).on("change", "#year_select", function(){
            init("#month_select");
            addMonth();
        });

        // 도/특별시
        $(document).on("change", "#do_select", function(){
            init("#si_select");

            var region = $(this).val();
            var keys = Object.keys(area[region]);
            keys.forEach(function(si){
                $("#si_select").append("<option value="+area[region][si]+">"+area[region][si]+"</option>");
            });
        });

    }