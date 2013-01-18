get_$field_name$_from_pb_object(p_cpp_obj:POINTER):INTEGER_32
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_message_type_name$* objPtr = ($pb_message_type_name$ *)\$p_cpp_obj;						
		return objPtr->$field_name$();
	]"
end