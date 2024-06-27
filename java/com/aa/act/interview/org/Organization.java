package com.aa.act.interview.org;

import java.util.Optional;
import java.util.Random;
import java.util.Collection;

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
    Position hired = new Position("");
    public Optional<Position> hire(Name person, String title) {
    	if(root.getTitle().equals(title))
		{
			//create a 6 digit id for the employee
			Random rnd = new Random();
			int n = 100000 + rnd.nextInt(900000);
			root.setEmployee(Optional.of(new Employee(n, person)));
			return Optional.of(root);
		}
		else
		{
			findPosition(root, title, person);
		}

    	return Optional.of(hired);
    }

    private void findPosition(Position root, String title, Name person)
	{
		Collection<Position> reports = root.getDirectReports();

		for(Position pos : reports)
		{
			if(pos.getTitle().equals(title))
			{
				Random rnd = new Random();
				int n = 100000 + rnd.nextInt(900000);
				pos.setEmployee(Optional.of(new Employee(n, person)));
				hired = pos;
				return;
			}
			findPosition(pos, title, person);
		}
	}

    @Override
    public String toString() {
        return printOrganization(root, "");
    }

    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
