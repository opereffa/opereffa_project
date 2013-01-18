cpp_get_$field_name$_at(p_cpp_obj:POINTER; p_value:INTEGER):STRING
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_parent_type$* objPtr = ($pb_parent_type$ *)\$p_cpp_obj;
		size_t len = objPtr->$field_name$(\$p_value).length(); 		
		char* tmp = new char [ len + 1 ];
		strcpy( tmp, objPtr->$field_name$(\$p_value).c_str() );
		
		EIF_REFERENCE strToReturn = eif_string(tmp);
		delete tmp;//no need for this string now
		return strToReturn;
	]"
end
