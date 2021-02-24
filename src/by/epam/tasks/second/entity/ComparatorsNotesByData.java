package by.epam.tasks.second.entity;

import java.util.Comparator;
import java.util.Date;

public class ComparatorsNotesByData implements Comparator<Note> {
	@Override
	public int compare(Note o1, Note o2) {
		Date date1 = o1.getDate();
		Date date2 = o2.getDate();
		return date1.compareTo(date2);
	}
}