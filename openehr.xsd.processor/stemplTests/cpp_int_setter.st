set_$field_name$_in_pb_object(p_cpp_obj:POINTER; p_value:INTEGER)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_message_type_name$* objPtr = ($pb_message_type_name$ *)\$p_cpp_obj;						
		objPtr->set_$field_name$(\$p_value);
	]"
end