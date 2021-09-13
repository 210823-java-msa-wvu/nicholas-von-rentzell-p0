package services;

import models.Instrument;
import repositories.InstrumentRepo;

public class InstrumentServices {

    InstrumentRepo instrumentRepo = new InstrumentRepo();

    public boolean instExist(String instrument) {

        Instrument i = instrumentRepo.getByInstrument(instrument);

        if (i != null) {
            return instrument.equals(i.getInstName());
        }
        return false;
    }
}
