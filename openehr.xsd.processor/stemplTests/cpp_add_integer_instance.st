cpp_add_$field_name$_to_pb_object(p_cpp_obj:POINTER; p_value:INTEGER_32)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_parent_type$* objPtr = ($pb_parent_type$ *)\$p_cpp_obj;			
		objPtr->add_$field_name$(\$p_value);
	]"
end