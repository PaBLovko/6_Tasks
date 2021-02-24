package by.epam.tasks.second.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteInfo extends Note {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String TITLE_PATTERN = "^[.+]{2,}";
	private static final String MESSAGE_PATTERN = "^[.+]{2,}";

	public NoteInfo() {}

	public void noteInfo() throws IOException {
		System.out.println("-------------");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter theme of note");

		super.setTheme(reader.readLine());
		super.setDate(Calendar.getInstance().getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = dateFormat.format(super.getDate());
		System.out.println("Date of note correctional  " + strDate);

		System.out.println("Enter e-mail");

		while (true) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			String email;
			email = reader.readLine();
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				super.setEmail(email);
				break;
			} else {
				System.out.println("Invalid e-mail");
			}
		}

		System.out.println("Enter massage");

		String message;
		message = reader.readLine();

		super.setMessage(message);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}