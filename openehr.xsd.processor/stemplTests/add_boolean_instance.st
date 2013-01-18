add_$field_name$(p_value:BOOLEAN)
do
	cpp_add_$field_name$_to_pb_object(get_protoc_ptr, p_value)
end