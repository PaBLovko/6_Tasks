package by.epam.tasks.second.entity;

import java.util.Comparator;
import java.util.Date;

public class ComparatorNoteByDataTitle implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		Date date1 = o1.getDate();
		Date date2 = o2.getDate();
		int rez = date1.compareTo(date2);
		if (rez != 0) {
			return rez;
		}
		return o1.getTheme().compareTo(o2.getTheme());
	}
}