add_$field_name$(p_value:STRING)
local
		p_value_to_c:ANY
do
	p_value_to_c := p_value.to_c
	cpp_add_$field_name$_to_pb_object(get_protoc_ptr, \$p_value_to_c)
end
