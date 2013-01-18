create_pb_object:POINTER
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_message_type_name$ *objPtr = new $pb_message_type_name$();
	  bosphorus::Person_PhoneNumber *phoneNo2 = person2->add_phone();
	  phoneNo2->set_number("12323");
	  bosphorus::Person_PhoneType phoneType2 = bosphorus::Person_PhoneType_MOBILE;
	  phoneNo2->set_type(phoneType2);
	  person2->set_id(123);
	  person2->set_email("initial email");
	  
	  const std::string* name = new std::string("this is the name from cpp code");		  
	  person2->set_name("lksdjflkasj");		  
	  person2->set_name(*name);
		
	  return person2;

	]"

end