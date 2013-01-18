get_$field_name$_from_pb_object(p_cpp_obj:POINTER):POINTER
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_message_type_name$* parentObjPtr = ($pb_message_type_name$*)\$p_cpp_obj;		  			
	  return parentObjPtr->mutable_$field_name$();
	]"
end