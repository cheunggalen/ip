public class Event extends Task {

    public Event(String description, String date, String time) {
        super(description, date, time, "[E]", true);
    }

    @Override
    public String timeFormatter(String time) {
        String[] sArr = time.split("-");
        String time1 = sArr[0];
        String time2 = sArr[1];
        char[] cArr = time1.toCharArray();
        Integer tensHour = cArr[0] - '0';
        Integer onesHour = cArr[1] - '0';
        Integer tensMin = cArr[2] - '0';
        Integer onesMin = cArr[3] - '0';
        String start = "";
        boolean isAfternoon = false;
        if (!tensHour.equals(0)) {
            Integer combinedHour = tensHour * 10 + onesHour;
            if (combinedHour >= 12) {
                isAfternoon = true;
                if (combinedHour >= 13) {
                    combinedHour -= 12;
                }
            }
            start += combinedHour.toString() + ":";
        } else {
            if (onesHour.equals(0)) {
                start += "12:";
            } else {
                start += onesHour.toString() + ":";
            }
        }
        if (tensMin == 0) {
            start += "0";
        } else {
            start += tensMin.toString();
        }
        start += onesMin.toString();
        if (isAfternoon) {
            start += "pm";
        } else {
            start += "am";
        }
        cArr = time2.toCharArray();
        tensHour = cArr[0] - '0';
        onesHour = cArr[1] - '0';
        tensMin = cArr[2] - '0';
        onesMin = cArr[3] - '0';
        String end = "";
        isAfternoon = false;
        if (!tensHour.equals(0)) {
            Integer combinedHour = tensHour * 10 + onesHour;
            if (combinedHour >= 12) {
                isAfternoon = true;
                if (combinedHour >= 13) {
                    combinedHour -= 12;
                }
            }
            end += combinedHour.toString() + ":";
        } else {
            if (onesHour.equals(0)) {
                end += "12:";
            } else {
                end += onesHour.toString() + ":";
            }
        }
        if (tensMin == 0) {
            end += "0";
        } else {
            end += tensMin.toString();
        }
        end += onesMin.toString();
        if (isAfternoon) {
            end += "pm";
        } else {
            end += "am";
        }
        return start + "-" + end;
    }
}