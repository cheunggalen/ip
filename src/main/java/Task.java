import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    private String description;
    private final String date;
    private final String time;
    private final String symbol;
    private boolean isAt;
    private boolean isDone;

    public Task(String description, String date, String time, String symbol, boolean flag) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.symbol = symbol;
        this.isAt = flag;
        this.isDone = false;
    }

    public Task(String description, String date, String time, String symbol, boolean flag, boolean done) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.symbol = symbol;
        this.isAt = flag;
        this.isDone = done;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    /*
     * Change the done status of a task.
     *
     * @return The same task with a changed done status.
     */
    public Task markAsDone() {
        return new Task(description, date, time, symbol, isAt, true);
    }

    /*
     * Convert the date from "DD/MM/YYYY" format to "D MMM YYYY" format.
     *
     * @param date Date in "DD/MM/YYYY" format.
     * @return Date in "D MMM YYYY" format.
     */
    public String dateFormatter(String date) {
        String year = "";
        String month = "";
        String day = "";
        String[] sArr = date.split("");
        int slashCounter = 0;
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i].equals("/")) {
                slashCounter++;
            } else if (slashCounter == 2) {
                year += sArr[i];
            } else if (slashCounter == 1) {
                month += sArr[i];
            } else {
                day += sArr[i];
            }
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String formattedDate = year + "-" + month + "-" + day;
        LocalDate ld = LocalDate.parse(formattedDate);
        return ld.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /*
     * Convert time from 24h format to 12h format.
     * Separate hour and minute by adding a colon.
     * Add am or pm depending on time of the day.
     *
     * @param time Time in 24h format.
     * @return Time in 12h format.
     */
    public String timeFormatter(String time) {
        char[] cArr = time.toCharArray();
        Integer tensHour = cArr[0] - '0';
        Integer onesHour = cArr[1] - '0';
        Integer tensMin = cArr[2] - '0';
        Integer onesMin = cArr[3] - '0';
        String output = "";
        boolean isAfternoon = false;
        if (!tensHour.equals(0)) {
            Integer combinedHour = tensHour * 10 + onesHour;
            if (combinedHour >= 12) {
                isAfternoon = true;
                if (combinedHour >= 13) {
                    combinedHour -= 12;
                }
            }
            output += combinedHour.toString() + ":";
        } else {
            if (onesHour.equals(0)) {
                output += "12:";
            } else {
                output += onesHour.toString() + ":";
            }
        }
        if (tensMin == 0) {
            output += "0";
        } else {
            output += tensMin.toString();
        }
        output += onesMin.toString();
        if (isAfternoon) {
            output += "pm";
        } else {
            output += "am";
        }
        return output;
    }

    @Override
    public String toString() {
        String copy = "";
        if (this.date.length() > 0) {
            if (this.isAt) {
                copy += "(at: ";
            } else {
                copy += "(by: ";
            }
            copy += this.dateFormatter(this.date);
            if (this.time.length() > 0) {
                copy += ", " + this.timeFormatter(this.time);
            }
            copy += ")";
        }
        copy = this.description + copy;
        if (this.isDone) {
            return symbol + "[X] " + copy;
        } else {
            return symbol + "[ ] " + copy;
        }
    }
}
