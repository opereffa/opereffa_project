cpp_get_$field_name$_size_from_pb_object(p_cpp_obj:POINTER):INTEGER_32
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_parent_type$* objPtr = ($pb_parent_type$ *)\$p_cpp_obj;			
		return objPtr->$field_name$().size();
	]"
end