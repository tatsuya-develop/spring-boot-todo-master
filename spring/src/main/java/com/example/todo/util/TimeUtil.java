package com.example.todo.util;

import java.time.format.DateTimeFormatter;
// TemporalAccessor は LocalDateTime, LocalDate, ZonedDateTime などが入り、時間を表すインターフェース。
import java.time.temporal.TemporalAccessor;

public class TimeUtil {
  public static final String ymd = "yyyy-MM-dd";
  public static final String ymdhm = "yyyy-MM-dd HH:mm";
  public static final String ymdhms = "yyyy-MM-dd HH:mm:ss";
  public static final String ym = "yyyy-MM";
  public static final String ymdI = "yyyyMMdd";
  public static final String ymI = "yyyyMM";

  public class Format {

    /**
     * 時間を yyyy-MM-dd 形式の文字列に変換する。
     *
     * @param time 時間
     * @return yyyy-MM-dd 形式の文字列
     */
    public static String toYmd(TemporalAccessor time) {
      return format(time, ymd);
    }

    /**
     * 時間を yyyy-MM-dd HH:mm 形式の文字列に変換する。
     *
     * @param time
     * @return yyyy-MM-dd HH:mm 形式の文字列
     */
    public static String toYmdhm(TemporalAccessor time) {
      return format(time, ymdhm);
    }

    /**
     * 時間を yyyy-MM-dd HH:mm:ss 形式の文字列に変換する。
     *
     * @param time
     * @return yyyy-MM-dd HH:mm:ss 形式の文字列
     */
    public static String toYmdhms(TemporalAccessor time) {
      return format(time, ymdhms);
    }

    /**
     * 時間を yyyy-MM 形式の文字列に変換する。
     *
     * @param time
     * @return yyyy-MM 形式の文字列
     */
    public static String toYm(TemporalAccessor time) {
      return format(time, ym);
    }

    /**
     * 時間を yyyyMMdd 形式の文字列に変換する。
     *
     * @param time
     * @return yyyyMMdd 形式の文字列
     */
    public static Integer toYmdI(TemporalAccessor time) {
      return Integer.parseInt(format(time, ymdI));
    }

    /**
     * 時間を yyyyMM 形式の文字列に変換する。
     *
     * @param time
     * @return yyyyMM 形式の文字列
     */
    public static Integer toYmI(TemporalAccessor time) {
      return Integer.parseInt(format(time, ymI));
    }

    /**
     * 時間を指定されたフォーマットの文字列に変換する。
     *
     * @param time 時間
     * @param format フォーマット
     * @return フォーマットされた文字列
     */
    private static String format(TemporalAccessor time, String format) {
      return DateTimeFormatter.ofPattern(format).format(time);
    }
  }
}
