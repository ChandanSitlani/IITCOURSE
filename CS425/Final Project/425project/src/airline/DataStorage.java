package airline;

import java.util.ArrayList;

import DataBase.DataHelper;
import models.*;

public class DataStorage {
	protected UserInfo userInfo;
	protected ArrayList<String> countries;
	protected static DataStorage dStorage;
	protected DataHelper helper;
}
