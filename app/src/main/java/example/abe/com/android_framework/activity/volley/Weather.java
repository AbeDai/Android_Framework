package example.abe.com.android_framework.activity.volley;

/**
 * Created by Administrator on 2016/8/1.
 */
public class Weather {
    public WeatherInfo weatherinfo;

    public WeatherInfo getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherInfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString(){
        return "WeatherInfo" + "-------" + weatherinfo.toString();
    }
}

class WeatherInfo{
    public String city;
    public String cityid;
    public String temp;
    public String WD;
    public String WS;
    public String SD;
    public String WSE;
    public String isRadar;
    public String Radar;
    public String njd;
    public String qy;
    public String rain;
    public String time;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }

    public String getWSE() {
        return WSE;
    }

    public void setWSE(String WSE) {
        this.WSE = WSE;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getRadar() {
        return Radar;
    }

    public void setRadar(String radar) {
        Radar = radar;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return "city" + city + "\n"
                + "cityid" + cityid + "\n"
                + "temp" + temp + "\n"
                + "WD" + WD + "\n"
                + "WS" + WS + "\n"
                + "SD" + SD + "\n"
                + "WSE" + WSE + "\n"
                + "isRadar" + isRadar + "\n"
                + "Radar" + Radar + "\n"
                + "njd" + njd + "\n"
                + "qy" + qy + "\n"
                + "rain" + rain + "\n"
                + "time" + time + "\n";
    }
}