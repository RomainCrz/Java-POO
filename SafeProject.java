import java.util.Scanner;

import java.util.ArrayList;

class SafeProject {

	private final static int NB_PROJECTS = 3;

	public static void main(String[] args) {
		ArrayList<Project> projects = new ArrayList<Project>();

		do {
			Project project = new Project();
			project.readProject();
			projects.add(project);
		} while (projects.size() < NB_PROJECTS);
	}

}

class Project {

	private String name = null;
	private String subject = null;
	private int duration = -1;

	public Project() {
	}

	public void readProject() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Donnez le nom du projet : ");
			try {
				name = scanner.nextLine();
				if (name.length() > 50) {
					throw new NameTooLongException("Nom trop long");
				}
			} catch (NameTooLongException e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Donnez le sujet du projet : ");
			try {
				subject = scanner.nextLine();
				if (subject.length() > 50) {
					throw new NameTooLongException("Sujet trop long");
				}
			} catch (NameTooLongException e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Donnez la durée du projet : ");
			try {
				duration = scanner.nextInt();
				if (duration < 0) {
					throw new WrongDurationException("Mauvaise durée !");
				}
			} catch (WrongDurationException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}

class NameTooLongException extends Exception {
	public NameTooLongException() {
		super();
	}

	public NameTooLongException(String s) {
		super(s);
	}
}

class WrongDurationException extends Exception {
	public WrongDurationException() {
		super();
	}

	public WrongDurationException(String s) {
		super(s);
	}
}
