cpp_serialize_pb_object_to_string(pb_object:POINTER):STRING
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_object_type$* pbObj  = ($pb_object_type$*)\$pb_object;	  
	  //serialize to string 
	  std::string s = pbObj -> SerializeAsString();		 
	  size_t len = s.length();
	  char* tmp = new char [ len ]; //normally it should be len + 1, but this is not supposed to be used as a string, it is just for serialization
	  //strcpy( tmp, s.c_str() );//terminates string when it sees a 0, but serialization may add 0s all along the char array, so use memcpy
	  memcpy( (void*)tmp, s.c_str(), len);
	  
	  //the following is for debug use, to inspect the content of string serialization
	  //std::ofstream myfile;
	  //myfile.open ("c:\\tmp\\pbserialized.txt",std::ios::binary);
	  //myfile.write(tmp, len);	  
	  //myfile.close();
	  
	  //EIF_REFERENCE strToReturn = eif_string(tmp); //eif_string uses strcpy, and terminates the string when it sees a 0. Serialization of 
	  //boolean fields may introduce 0s, so we use RTMS_EX
	  
	  EIF_REFERENCE strToReturn = RTMS_EX(tmp, len);
	  delete[] tmp;//no need for this char array now
	  return strToReturn;
	  
	]"

end
