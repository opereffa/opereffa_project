get_$field_name$_at(p_value:INTEGER):STRING
do
	Result := cpp_get_$field_name$_at(get_protoc_ptr, p_value)		
end