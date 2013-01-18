get_$field_name$_from_pb_object(p_cpp_obj:POINTER):INTEGER_32
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$field_type$* objPtr = ($field_type$*)\$p_cpp_obj;
		int val = $enum_default_value$;//default val here
		$enum_cpp_getter_if_statement$
		$enum_cpp_getter_elseif_statements; separator="\n"$
		return val;
	]"
end