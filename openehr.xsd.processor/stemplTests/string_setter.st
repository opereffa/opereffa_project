set_$field_name$(p_value: STRING)
local
	p_value_to_c:ANY
do
	p_value_to_c := p_value.to_c
	set_$field_name$_in_pb_object(get_protoc_ptr, \$p_value_to_c, p_value.count)
end