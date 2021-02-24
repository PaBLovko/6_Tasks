package by.epam.tasks.second;

import by.epam.tasks.second.DAO.NoteDAO;
import by.epam.tasks.second.entity.*;
import by.epam.tasks.second.impl.FileNoteDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Разработать консольное приложение, работающее с Заметками в Блокноте.
 * Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
 * Общие пояснения к практическому заданию:
 * • В начале работы приложения данные должны считываться из файла, в конце 
 * работы – сохраняться в файл.
 * • У пользователя должна быть возможность найти запись по любому параметру
 * или  по  группе  параметров  (группу  параметров  можно  определить
 * самостоятельно), получить требуемые записи в отсортированном виде, найти
 * записи,  текстовое  поле  которой  содержит  определенное  слово,  а  также
 * добавить новую запись.
 * • Особое  условие:  поиск,  сравнение  и  валидацию  вводимой  информации
 * осуществлять с использованием регулярных выражений.
 * • Особое  условие:  проверку  введенной  информации  на  валидность  должен
 * осуществлять код, непосредственно добавляющий информацию.
 * */

public class Runner {

	public static void main(String[] args) throws IOException {
		NoteDAO noteDAO = new FileNoteDAO();
		List<Note> noteList = new ArrayList<>();
		if (noteDAO.getAll() != null) {
			noteList.addAll(noteDAO.getAll());
		}
		String str = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		do {

			System.out.println("1)Enter note ");
			System.out.println("2)Enter all notes ");
			System.out.println("3)Sort all notes by date and name ");
			System.out.println("4)Sort all notes by e-mail and date ");
			System.out.println("5)Sortr all by date ");
			System.out.println("-------------");

			int keys = 0;
			try {
				keys = Integer.parseInt(reader.readLine());
			}

			catch (NumberFormatException e) {
				System.out.println("Incorrect number, try again.");
			}

			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Incorrect choice of number, try again. ");
			}

			switch (keys) {
			case (1):
				NoteInfo note = new NoteInfo();
				note.noteInfo();
				noteList.add(note);

				break;

			case (2):
				for (Note s : noteList)
					System.out.println(s.toString());
				break;

			case (3):
				ComparatorNoteByDataTitle comparatorNoteByDataTitle = new ComparatorNoteByDataTitle();
				noteList.sort(comparatorNoteByDataTitle);
				break;

			case (4):
				ComparatorNotesByEmailData comparatorNotesByEmailData = new ComparatorNotesByEmailData();
				noteList.sort(comparatorNotesByEmailData);
				break;

			case (5):
				ComparatorsNotesByData comparatorsNotesByData = new ComparatorsNotesByData();
				noteList.sort(comparatorsNotesByData);
				break;

			default:
				System.out.println("Incorrect choice, try again.");
				break;
			}

			System.out.print("End  Y/N: ");

			try {
				str = reader.readLine();
			}

			catch (IOException e) {
				e.printStackTrace();
				System.out.print("Incorrect input : ");
			}
		} while (!str.equals("Y"));
		noteDAO.saveAll(noteList);
	}
}