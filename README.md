# DateConvertors

Custom Read and Write Java Date convertors to save and read UTC dates to/from DB.

UTC date : **2022-01-27T20:06:48.000Z** will be saved in DB as **2022-01-27T20:06:48.000+00:00**


To register convertors with Mongo DB with spring Boot.

	@Override
	public MongoCustomConversions customConversions() {
	    converters.add(new ZonedDateTimeReadConverter());
	    converters.add(new ZonedDateTimeWriteConverter());
	    return new MongoCustomConversions(converters);
	}

