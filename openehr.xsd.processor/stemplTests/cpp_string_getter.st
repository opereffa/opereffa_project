get_$field_name$_from_pb_object(p_cpp_obj:POINTER): STRING
external
	"C++ inline use %"$header_file_name$%""
alias "[
		$pb_message_type_name$* objPtr = ($pb_message_type_name$ *)\$p_cpp_obj;
		size_t len = objPtr->$field_name$().length();
		char* tmp = new char [ len + 1 ];
		strcpy( tmp, objPtr->$field_name$().c_str() );
		
		EIF_REFERENCE strToReturn = eif_string(tmp);
		delete[] tmp;//no need for this char arr now
		return strToReturn;
	]"
end