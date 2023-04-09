package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;

	public Organization() {
		root = createOrganization();
	}

	protected abstract Position createOrganization();

	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		if (person == null)
			throw new IllegalArgumentException("Name cannot be null");
		Optional<Employee> newEmployee = Optional.of(new Employee(person));

		if (root.getTitle() != title) {
			for (Position x : root.getDirectReports()) {
				if (x.getTitle() != title) {
					for (Position y : x.getDirectReports()) {
						if (y.getTitle() != title) {
							for (Position z : y.getDirectReports()) {
								if (z.getTitle() == title) {
									z.setEmployee(newEmployee);
								}
							}
						} else {
							y.setEmployee(newEmployee);
							return Optional.of(y);
						}
					}
				} else {
					x.setEmployee(newEmployee);
					return Optional.of(x);
				}
			}
		} else {
			root.setEmployee(newEmployee);
			return Optional.of(root);
		}

		return Optional.empty();
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}

	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for (Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
