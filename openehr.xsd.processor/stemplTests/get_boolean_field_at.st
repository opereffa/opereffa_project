get_$field_name$_at(p_value:INTEGER):BOOLEAN
do
	Result := cpp_get_$field_name$_at(get_protoc_ptr, p_value)		
end