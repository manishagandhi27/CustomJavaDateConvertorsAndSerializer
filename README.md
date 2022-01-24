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

**Millisecond**  is a  Custom Serializer to maintain milliseconds in date format.
 *  As per ISO-8601 date can be any format 
 *  uuuu-MM-dd'T'HH:mm
 *  uuuu-MM-dd'T'HH:mm:ss
*   uuuu-MM-dd'T'HH:mm:ss.SSS
*   uuuu-MM-dd'T'HH:mm:ss.SSSSSS
*   uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS
*   e.g if date = 2022-01-24T12:00:00.000+00:00 , output from date instant class would be 2022-01-24T12:00
*   if date = 2022-01-27T20:06:48.000+00:00, output would be 2022-01-27T20:06:48
*   Below Serializer takes care of formatting date to format yyyy-MM-dd'T'HH:mm:ss.SSS and appending 3digit milliseconds if all zero.
