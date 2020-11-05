package com.scalesampark.service;

import com.scalesampark.dto.domain.Participant;
import com.scalesampark.exception.ParticipentNotFoundException;

public abstract class AbstractSamparkService {

	public boolean isValidParticipant(Participant p)  {
		if(null!=p) {
			return true;
		}
		throw new ParticipentNotFoundException("Paricipent with given id not found");
	}
}
