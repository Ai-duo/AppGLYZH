package com.kd.appglyzh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Info {
    public String site_item;
    public String site_name;
    public String site_logo;
    public String updatetime;
    public String nongdu;
    public String ndu;
    public String wdu;
    public String sdu;
    public String  getTime(){
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 HH时mm分", Locale.CHINA);

        String dt = sdf2.format(new Date());
        return dt;

    }
}
