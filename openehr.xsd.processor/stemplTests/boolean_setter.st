set_$field_name$(p_value:BOOLEAN)
do
	set_$field_name$_in_pb_object(get_protoc_ptr, p_value);
end