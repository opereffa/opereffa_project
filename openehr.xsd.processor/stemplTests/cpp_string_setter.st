set_$field_name$_in_pb_object(p_cpp_obj:POINTER; c_str:POINTER; size:INTEGER)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_message_type_name$* objPtr = ($pb_message_type_name$ *)\$p_cpp_obj;
		
		char* str = new char [ \$size ];//this str will belong to protocol buffer object, so don't delete it here.
		memcpy( (void*)str, \$c_str, \$size);												
		objPtr->set_$field_name$(str,\$size);
		
		//the following block is now obsolete, since it can't
		//properly handle string serialization of binary data. 
		
		//copy str to make sure that it is not collected by eiffel somewhere else
		//std::string* str = new std::string((char*)\$c_str);		
		//objPtr->set_$field_name$(str->c_str());
		//delete str;
	]"
end