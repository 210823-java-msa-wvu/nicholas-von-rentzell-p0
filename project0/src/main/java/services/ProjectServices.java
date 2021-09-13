package services;

import models.Project;
import repositories.ProjectRepo;

public class ProjectServices {

    ProjectRepo projectRepo = new ProjectRepo();

    public boolean projectExist(String pName) {

        Project p = projectRepo.getByProject(pName);

        return pName.equals(p.getProjName());
    }
}
