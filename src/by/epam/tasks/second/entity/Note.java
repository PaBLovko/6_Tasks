package by.epam.tasks.second.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparator<Note> {
	private String theme;
	private Date date;
	private String email;
	private String message;

	public Note() {}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int compare(Note o1, Note o2) {
		return o1.getTheme().compareTo(o2.getTheme());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Note note = (Note) o;
		return Objects.equals(theme, note.theme) &&
				Objects.equals(date, note.date) &&
				Objects.equals(email, note.email) &&
				Objects.equals(message, note.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(theme, date, email, message);
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = dateFormat.format(date);

		return "theme=" + theme + "|" + ", date=" + strDate + "|" + ", email=" + email + "|" + ", mesage=" + message
				+ "|";
	}
}