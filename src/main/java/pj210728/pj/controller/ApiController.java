package pj210728.pj.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ApiController {
    @GetMapping(value = "/apiTest", produces = "application/text; charset=utf8")
    public String callApiHttp(@RequestParam("ymd") String ymd, @RequestParam("cd") String cd) {
        StringBuffer result = new StringBuffer();

        try {
            String base = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
            String serviceKey = "?serviceKey=uzqVSGAn51ZoLPRRxN3%2BrS47DIR%2FD7HyH1lSAzGAHSAqhq4zjnlcq%2FpPlCLyApT8M%2B4VQ%2BHMHvSZVGdSE%2FjcrQ%3D%3D";
            String pageNo = "&pageNo=1"; // 페이지 번호
            String numOfRows = "&numOfRows=10"; // 한 페이지 결과 수
            String lawd = "&LAWD_CD="; // 지역 코드
            String deal = "&DEAL_YMD="; // 계약월
            String urlStr = base + serviceKey + pageNo + numOfRows +
                    lawd + cd + deal + ymd;
            URL url = new URL(urlStr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
