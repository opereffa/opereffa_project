cpp_add_$field_name$_to_pb_object(p_cpp_obj:POINTER; p_value:POINTER)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_parent_type$* objPtr = ($pb_parent_type$ *)\$p_cpp_obj;			
		std::string *addedStr = objPtr->add_$field_name$();
		//copy str to make sure that it is not collected by eiffel somewhere else
		std::string* tempStr = new std::string((char*)\$p_value);
		*addedStr = tempStr -> c_str();
		delete tempStr;
	]"
end
