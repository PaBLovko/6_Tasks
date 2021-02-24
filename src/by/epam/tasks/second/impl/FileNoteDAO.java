package by.epam.tasks.second.impl;

import by.epam.tasks.second.DAO.NoteDAO;
import by.epam.tasks.second.entity.Note;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileNoteDAO implements NoteDAO {

	@Override
	public boolean add(Note note) {

		Writer writer;
		try {
			String str = note.toString();
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("..\\resources\\database.txt", true), StandardCharsets.UTF_8));
			writer.write(str);
			writer.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("Error of input-output: ");
			return false;
		} catch (IOException e) {
			System.out.println("Error of input-output: ");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean remove(Note note) {
		return false;
	}

	@Override
	public List<Note> getAll() {
		List<Note> listNote = new ArrayList<>();

		String s;
		StringBuilder listNotes = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader("..\\resources\\database.txt"));
			while ((s = br.readLine()) != null) {
				listNotes.append(s).append("\n");
			}

			String[] loginListAr = listNotes.toString().split("\n");
			for (String count : loginListAr)
				listNote.add(toNote(count));
			br.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("Error of input-output: ");
			return null;
		} catch (IOException e) {
			System.out.println("Error of input-output: ");
			e.printStackTrace();
			return null;
		}
		return listNote;
	}

	@Override
	public Note toNote(String note) {
		Note userNote = new Note();
		String[] noteSplit = note.split("\\|");

		userNote.setTheme(noteSplit[0].split("=")[1]);
		try {
			userNote.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(noteSplit[1].split("=")[1]));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		userNote.setEmail(noteSplit[2].split("=")[1]);
		userNote.setMessage(noteSplit[3].split("=")[1]);

		return userNote;
	}

	@Override
	public void saveAll(List<Note> noteList) {
		Writer writer;
		try {
			StringBuilder str = new StringBuilder();
			for (Note s : noteList) {
				str.append(s.toString()).append("\n");
			}

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("database.txt", false), StandardCharsets.UTF_8));
			writer.write(str.toString());
			writer.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("Error of input-output: ");
        } catch (IOException e) {
			System.out.println("Error of input-output: ");
			e.printStackTrace();
        }
    }
}