set_$field_name$_in_pb_object(p_cpp_obj:POINTER; p_value:INTEGER)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$field_type$* objPtr = ($field_type$ *)\$p_cpp_obj;
		$enum_type$ enumType; 
		
		$enum_cpp_setter_if_statement$
		$enum_cpp_setter_elseif_statements; separator="\n"$
			
		objPtr->set_$field_name$(enumType);

	]"
end	