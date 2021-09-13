package services;

import models.Loop;
import repositories.LoopRepo;

public class LoopServices {

    LoopRepo userRepo = new LoopRepo();

    public boolean loopExist(String loop) {

        Loop l = userRepo.getByLoop(loop);

        if (l != null) {
            return loop.equals(l.getLoopName());
        }
        return false;
    }
}
