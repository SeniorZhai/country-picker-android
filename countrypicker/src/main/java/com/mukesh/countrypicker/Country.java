package com.mukesh.countrypicker;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

/**
 * Created by mukesh on 25/04/16.
 */

public class Country {
  private String code;
  private String name;
  private String dialCode;
  // sorted by English name
  private String englishName;
  private int flag;

  public String getDialCode() {
    return dialCode;
  }

  public void setDialCode(String dialCode) {
    this.dialCode = dialCode;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code, String countryName) {
    this.code = code;
    if (TextUtils.isEmpty(name)) {
      if (code.equals("BQ")) {
        name = countryName;
        englishName = countryName;
      } else {
        name = new Locale("", code).getDisplayName();
        englishName = new Locale("en", code).getDisplayName(Locale.getDefault());
        englishName = englishName.substring(englishName.indexOf('(') + 1);
      }
    }
  }

  public String getName() {
    return name;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public String getEnglishName() {
    return englishName;
  }
}