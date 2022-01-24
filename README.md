# DateConvertors

Custom Read and Write Java Date convertors.

To register convertors with Mongo DB

	@Override
	public MongoCustomConversions customConversions() {
	    converters.add(new ZonedDateTimeReadConverter());
	    converters.add(new ZonedDateTimeWriteConverter());
	    return new MongoCustomConversions(converters);
	}
