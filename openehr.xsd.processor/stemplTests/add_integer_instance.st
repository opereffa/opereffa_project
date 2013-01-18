add_$field_name$(p_value:INTEGER_32)
do
	cpp_add_$field_name$_to_pb_object(get_protoc_ptr, p_value)
end