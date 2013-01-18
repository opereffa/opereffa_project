package bosphorus;
//REMEMBER: namespace = and operator = should be prepended with _ or something else, these are keywords for c++ (at least under windows with MS compiler)
$message; separator="\n"$


//these messages are used by java to eiffel comm. 
message RequestHeader{
	required string url = 1;
}

message Parameters{
	repeated string content = 1;
}

message ResponseHeader{
	enum Status{
		PB_OK = 1;
		PB_ERROR = 2;
		PB_WARNING = 3;
		PB_INFO = 4;
	}
	required Status responsestatus = 1 [default = PB_OK];
	required string content = 2;
}

message ArchetypeList{
	repeated string names = 1;
}

message RAWDATA{
	required string typeinfo = 1;
	optional bytes msgdata = 2;
	optional string stringvalue = 3;
	optional int32 integervalue = 4;

}