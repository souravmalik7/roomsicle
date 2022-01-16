package controller.filterroommates;

import java.util.HashMap;

public interface IFilterRoommates {

	HashMap<String, Integer> filterRoommates(IFilterRoommatesInput preferences);
	
}
