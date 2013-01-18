add_$field_name$_to_pb_object(p_cpp_obj:POINTER):POINTER
external
	"C++ inline use %"$header_file_name$%""
alias "[
	$pb_parent_type$* parentObjPtr = ($pb_parent_type$*)\$p_cpp_obj;
	$field_type$ * newObjPtr = parentObjPtr->add_$field_name$();
	return newObjPtr;
	]"
end